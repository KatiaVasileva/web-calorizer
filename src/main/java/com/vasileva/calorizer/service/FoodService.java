package com.vasileva.calorizer.service;

import com.vasileva.calorizer.exception.FoodExistsException;
import com.vasileva.calorizer.exception.FoodNotFoundException;
import com.vasileva.calorizer.mapper.FoodMapper;
import com.vasileva.calorizer.model.food.Food;
import com.vasileva.calorizer.model.food.FoodIn;
import com.vasileva.calorizer.model.food.FoodOut;
import com.vasileva.calorizer.repository.FoodRepository;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodService {

    public final FoodRepository foodRepository;
    public final FoodMapper foodMapper;
    private final MeterRegistry meterRegistry;

    @Transactional
    public FoodOut addFood(FoodIn input) {
        Food entity = foodRepository.save(foodMapper.in(input));
        meterRegistry.counter("list.food.added",
                "category", input.getFoodCategory().name(), // Разделит метрику по категориям
                "status", "success"
        ).increment();
        return foodMapper.out(entity);
    }

    public List<FoodOut> getAllFoods() {
        return foodRepository
                .findAll()
                .stream()
                .map(foodMapper::out)
                .collect(Collectors.toList());
    }

    public List<FoodOut> getAllSortedByField(String field) {
        Sort sort;
        if (Objects.equals(field, "isFavorite") || Objects.equals(field, "createdAt")) {
            sort = Sort.by(Sort.Direction.DESC, field);
        } else {
            sort = Sort.by(Sort.Direction.ASC, field);
        }
        return foodRepository.findAll(sort)
                .stream()
                .map(foodMapper::out)
                .toList();
    }

    public Page<FoodOut> getAllSortedByFieldWithPagination(String field, int page, int size) {
        Sort.Direction direction;
        if (Objects.equals(field, "isFavorite") || Objects.equals(field, "createdAt")) {
            direction = Sort.Direction.DESC;
        } else {
            direction = Sort.Direction.ASC;
        }
        Sort sort = Sort.by(direction, field);
        Pageable pageable = PageRequest.of(page, size, sort);
        return foodRepository.findAll(pageable)
                .map(foodMapper::out);
    }

    public FoodOut getFoodById(Long id) {
        return foodRepository.findById(id).map(foodMapper::out)
                .orElseThrow(() -> new FoodNotFoundException(String.format("Food with id=%d not found", id)));
    }

    @Transactional
    public FoodOut updateFood(FoodIn input, Long id) {
        Food updatedFood = foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(String.format("Food with id=%d not found", id)));
        if (!updatedFood.getName().equals(input.getName())) {

            boolean exists = foodRepository.existsByName(input.getName());

            if (exists) {
                throw new FoodExistsException(String.format("Food with name=%s already exists", input.getName()));
            }
        }

        foodMapper.update(input, updatedFood);
        return foodMapper.out(foodRepository.save(updatedFood));
    }

    @Transactional
    public FoodOut toggleFavorite(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(String.format("Food with id=%d not found", id)));

        food.setIsFavorite(!food.getIsFavorite());
        food.setUpdatedAt(LocalDateTime.now());

        return foodMapper.out(foodRepository.save(food));
    }

    @Transactional
    public void deleteFoodById(Long id) {
        foodRepository.deleteById(id);
    }


}
