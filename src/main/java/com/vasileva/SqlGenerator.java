package com.vasileva;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlGenerator {
    public static void main(String[] args) {
        String rawData =
                """
                        Food almond = new Food("Миндаль сырой", "Жизнь Март", FoodCategory.NUTS, 610, 18, 53, 14);
                        Food almondMilk = new Food("Растительный напиток миндальный", "Самокат", FoodCategory.GROCERIES, 40, 0.5, 2, 5);
                        Food apple = new Food("Яблоко", "", FoodCategory.FRUIT, 44.4, 0.4, 0.4, 9.8);
                        Food appleVinegar = new Food("Яблочный уксус", "Kühne", FoodCategory.GROCERIES, 24, 0, 0, 1.5);
                        Food avocado = new Food("Авокадо", "", FoodCategory.VEGETABLES, 160, 2, 14.7, 1.8);
                        Food bakingPowder = new Food("Разрыхлитель для теста", "Dr.Bakers", FoodCategory.GROCERIES, 79, 0.2, 0, 19.6);
                        Food baguette = new Food("Батон Подмосковный", "Смак", FoodCategory.PASTRIES, 260, 7.5, 2.5, 51);
                        Food balsamicSauce = new Food("Соус бальзамический", "Dolce Albero", FoodCategory.GROCERIES, 51, 0.2, 0, 51);
                        Food balsamicVinegar = new Food("Уксус бальзамический", "Dolce Albero", FoodCategory.GROCERIES, 70, 0.8, 0, 12);
                        Food banana = new Food("Банан", "", FoodCategory.FRUIT, 96, 1.5, 0.5, 21);
                        Food basil = new Food("Базилик", "", FoodCategory.VEGETABLES, 23, 3.2, 0.6, 2.7);
                        Food beansRed = new Food("Фасоль красная", "Бондюэль", FoodCategory.VEGETABLES, 87, 5.3, 0.2, 16);
                        Food bellPepper = new Food("Болгарский перец", "", FoodCategory.VEGETABLES, 27, 1.3, 0.1, 5.3);
                        Food beetroot = new Food("Свекла", "", FoodCategory.VEGETABLES, 43, 1.6, 0.2, 6.8);
                        Food beetrootBoiled = new Food("Свекла вареная", "", FoodCategory.VEGETABLES, 49, 1.8, 0.1, 10.8);
                        Food blackCurrant = new Food("Черная смородина", "", FoodCategory.BERRY, 44, 1, 0.4, 7.3);
                        Food bluecheese = new Food("Сыр мягкий Royal Bleu", "President", FoodCategory.MILK_PRODUCTS, 355, 19, 31, 4);
                        Food bread = new Food("Хлеб", "", FoodCategory.PASTRIES, 285.2, 8.2, 5.2, 46.7);
                        Food breadRings = new Food("Баранки со вкусом ванили", "Самокат", FoodCategory.PASTRIES, 330, 9, 2.5, 69);
                        Food broccoli = new Food("Брокколи", "", FoodCategory.VEGETABLES, 31, 2.6, 0.3, 3.9);
                        Food buckwheat = new Food("Гречка сырая", "Першинская", FoodCategory.GROCERIES, 350, 13, 3, 68);
                        Food buckwheat2 = new Food("Гречка сырая", "Увелка", FoodCategory.GROCERIES, 340, 13, 2.5, 61);
                        Food butter72 = new Food("Сливочное масло 72,5%", "Ирбитский", FoodCategory.MILK_PRODUCTS, 662, 1, 72.5, 1.4);
                        Food cabbage = new Food("Капуста белокочанная", "", FoodCategory.VEGETABLES, 25, 1.8, 0.1, 4.7);
                        Food caciotta = new Food("Сыр качотта с пажитником", "Cheesaro", FoodCategory.MILK_PRODUCTS, 305, 27, 21, 2.1);
                        Food cakeMixLemon = new Food("Смесь для лимонного кекса", "Печем дома", FoodCategory.PASTRIES, 355, 6, 1, 80);
                        Food cakeMixOrange = new Food("Смесь для апельсинового кекса", "Печем дома", FoodCategory.PASTRIES, 351, 6, 1, 80);
                        Food cakeMixRaspberry = new Food("Смесь для малинового кекса", "Печем дома", FoodCategory.PASTRIES, 350, 7, 1, 78);
                        Food cakeMixVanilla = new Food("Смесь для ванильного кекса", "Печем дома", FoodCategory.PASTRIES, 357, 6, 1, 82);
                        Food camembert = new Food("Сыр Petit Camamber", "Ile de France", FoodCategory.MILK_PRODUCTS, 293, 20, 24, 0.1);
                        Food camembert2 = new Food("Сыр Камамбер с черным трюфелем", "Жуковское молоко", FoodCategory.MILK_PRODUCTS, 317, 18, 27, 0.5); // 125 g
                        Food cannedCorn = new Food("Кукуруза консервированная", "Bonduelle", FoodCategory.VEGETABLES, 73, 2.7, 1.4, 11);
                        Food cannedTuna = new Food("Филе тунца в собственном соку (консервы рыбные)", "Самокат", FoodCategory.FISH, 72.7, 16.2, 0.4, 0.8);
                        Food cannedTunaFortuna = new Food("Тунец Yellowfin в собственном соку", "Fortuna", FoodCategory.FISH, 103, 24.6, 0.4, 0);
                        Food cannedTuna3 = new Food("Тунец рубленый натуральный", "Вкусные консервы", FoodCategory.FISH, 132, 23, 1, 0);
                        Food capers = new Food("Каперсы в винном уксусе", "Dolce Albero", FoodCategory.VEGETABLES, 24, 1.8, 0.4, 16);
                        Food capers2 = new Food("Каперсы большие", "Federici", FoodCategory.VEGETABLES, 22.7, 1.3, 0.3, 3.7);
                        Food carbonade = new Food("Карбонад свиной", "Сибагро", FoodCategory.MEAT, 164, 14, 12, 0);
                        Food carrot = new Food("Морковь", "", FoodCategory.VEGETABLES, 41, 0.9, 0.2, 6.8);
                        Food cheddar = new Food("Сыр чеддер Брест-Литовский", "Брест-Литовский", FoodCategory.MILK_PRODUCTS, 316, 26, 24.6, 0);
                        Food cheddar2 = new Food("Сыр чеддер Hochland", "Hochland", FoodCategory.MILK_PRODUCTS, 365, 23.7, 30, 0);
                        Food cheese = new Food("Сыр рассольный", "President", FoodCategory.MILK_PRODUCTS, 213, 12, 16.7, 3.7);
                        Food cheeseHolland = new Food("Сыр голландский", "Село Зеленое", FoodCategory.MILK_PRODUCTS, 329, 26, 25, 0);
                        Food cheeseSmoked = new Food("Сыр Косичка", "", FoodCategory.MILK_PRODUCTS, 276, 25.8, 19.2, 0);
                        Food cheeseSmokedMelted = new Food("Сыр плавленный колбасный", "Дружба", FoodCategory.MILK_PRODUCTS, 197, 12, 13, 8.5);
                        Food cherrySweet = new Food("Черешня", "", FoodCategory.FRUIT, 52, 1.1, 0.4, 10.6);
                        Food chicken = new Food("Куриная грудка", "Важная цыпа", FoodCategory.POULTRY, 154, 16, 10, 0);
                        Food chicken2 = new Food("Филе грудки цыпленка", "Троекурово", FoodCategory.POULTRY, 108, 21.3, 2.5, 0);
                        Food chicken3 = new Food("Филе грудки цыпленка", "Рефтинская", FoodCategory.POULTRY, 120, 19, 5, 0);
                        Food chickenGrilled = new Food("Куриная грудка гриль", "Жизнь Март", FoodCategory.POULTRY, 133, 27, 2.6, 0.5); // 1 portion = 150 g
                        Food chickenThigh = new Food("Бедро куриное>", "Важная Цыпа", FoodCategory.POULTRY, 150, 18, 8, 0);
                        Food chineseCabbage = new Food("Пекинская капуста", "", FoodCategory.GREENS, 16, 1.2, 0.2, 2);
                        Food chuckRoll = new Food("Говядина, мякоть подлопаточной части", "Заречный", FoodCategory.MEAT, 180, 18, 12, 0);
                        Food condensedMilk = new Food("Сгущенка", "Рогачев", FoodCategory.MILK_PRODUCTS, 330, 8, 8.5, 56);
                        Food cookieOatmeal = new Food("Печенье овсяное классическое", "Жизнь Март", FoodCategory.PASTRIES, 430, 7, 15.5, 66); // 1 pcs = 22 g
                        Food cookieOatmealWithDarkAndMilkChocolate = new Food("Печенье овсяное с темным и молочным шоколадом", "Жизнь Март", FoodCategory.PASTRIES, 460, 8, 20, 61.9); // 1 pcs = 24,3 g
                        Food cookieOatmealWithLime = new Food("Печенье овсяное с лаймом и помело", "Жизнь Март", FoodCategory.PASTRIES, 411, 8.4, 13.6, 63.6); // 1 pcs = 28.5 g
                        Food cookieOatmealWithPrune = new Food("Печенье овсяное с черносливом и грецким орехом", "Жизнь Март", FoodCategory.PASTRIES, 435, 11, 20.3, 52); // 1 pcs = 28.5 g
                        Food cookieWithMilkChocolate = new Food("Печенье с кусочками молочного шоколада", "Milka", FoodCategory.PASTRIES, 496, 6.9, 24, 63); // 1 pcs = 14 g
                        Food crabSticks = new Food("Крабовые палочки", "Русское море", FoodCategory.FISH, 91, 5.6, 1.2, 14.2);
                        Food cream10 = new Food("Сливки из Талицы пастеризованные, 10%", "Талицкие молочные фермы", FoodCategory.MILK_PRODUCTS, 118, 2.6, 10, 4.5);
                        Food cream20 = new Food("Сливки 20%", "Простоквашино", FoodCategory.MILK_PRODUCTS, 206, 2.6, 20, 4);
                        Food crispsRyeAndWheat = new Food("Хлебцы ржано-пшеничные с семенами", "Жизнь-Март", FoodCategory.PASTRIES, 458, 11.1, 15.7, 68.3); // 1 pcs = 10.6 g
                        Food crispsWithProvenceHerbs = new Food("Хлебцы с прованскими травами", "Kruazett", FoodCategory.PASTRIES, 330, 13, 3, 64); // 1 pcs = 5.9 g
                        Food croutons = new Food("Сухари из белого хлеба", "", FoodCategory.PASTRIES, 397, 10, 2.7, 73.8);
                        Food croutonsBorodinsky = new Food("Сухари из бородинского хлеба", "", FoodCategory.PASTRIES, 319.4, 10.6, 2.3, 63.9);
                        Food cucumber = new Food("Огурец", "", FoodCategory.VEGETABLES, 13, 0.8, 0.1, 1.7);
                        Food cucumberPickled = new Food("Огурцы маринованные по-берлински", "Дядя Ваня", FoodCategory.VEGETABLES, 14, 0, 0, 3.5);
                        Food curds = new Food("Творог обезжиренный", "Ирбитский", FoodCategory.MILK_PRODUCTS, 85, 18, 0, 3.3);
                        Food curds2 = new Food("Творог 2%", "Простоквашино", FoodCategory.MILK_PRODUCTS, 103, 18, 2, 3.3);
                        Food curds3 = new Food("Творог 1.8%", "Самокат", FoodCategory.MILK_PRODUCTS, 100, 18, 1.5, 3.3);
                        Food curds4 = new Food("Творог Село Зеленое 0,5%", "Село зеленое", FoodCategory.MILK_PRODUCTS, 74, 14, 0.5, 3.3);
                        Food curds5 = new Food("Творог Село Зеленое 5%", "Село зеленое", FoodCategory.MILK_PRODUCTS, 105, 12, 5, 3);
                        Food curdsGrainedWithStrawberry = new Food("Зерно твороженое клубника в сливках", "Село зеленое", FoodCategory.MILK_PRODUCTS, 123.8, 9.5, 4.2, 12);
                        Food curdsWithoutFat = new Food("Творог обезжиренный Exponenta", "Exponenta", FoodCategory.MILK_PRODUCTS, 70, 16, 0, 1.5);
                        Food curdsWithoutFatSoft = new Food("Мягкий творог обезжиренный", "Простоквашино", FoodCategory.MILK_PRODUCTS, 55, 10, 0.1, 3.4); // 120 g
                        Food dill = new Food("Укроп", "", FoodCategory.VEGETABLES, 43, 3.5, 1.1, 4.9);
                        Food doubleChickenOriginal = new Food("Дабл Чикен Оригинальный", "Rostics", FoodCategory.READY, 223, 20, 11, 8); // 214 g
                        Food driedFigs = new Food("Сушеный инжир", "Aregi", FoodCategory.FRUIT, 110, 1, 0, 25); // 1 pcs = 16 g
                        Food driedPlump = new Food("Чернослив", "", FoodCategory.FRUIT, 240, 2.3, 0.7, 57.5);
                        Food dryCuredTurkeyHam = new Food("Сыровяленая ветчина из индейки", "Индилайт", FoodCategory.MEAT, 150, 29, 4, 0);
                        Food egg = new Food("Яйцо", "Село зеленое", FoodCategory.EGGS, 157, 12.7, 11.5, 0.7);
                        Food eggWhite = new Food("Белок яйца", "Село зеленое", FoodCategory.EGGS, 52, 11, 0.2, 0.7); // 30 g
                        Food feta = new Food("Сыр Хоритаки", "Delissir", FoodCategory.MILK_PRODUCTS, 195, 11.5, 11.7, 11);
                        Food fetaksa = new Food("Сыр Фетакса", "Hochland", FoodCategory.MILK_PRODUCTS, 214, 12, 17.5, 2.3);
                        Food flour = new Food("Мука пшеничная высшего сорта", "Макфа", FoodCategory.GROCERIES, 340, 12, 1.1, 70.6);
                        Food garlic = new Food("Чеснок", "", FoodCategory.VEGETABLES, 149, 6.4, 0.5, 31);
                        Food gauda = new Food("Сыр гауда", "Село Зеленое", FoodCategory.MILK_PRODUCTS, 294, 26.7, 20.8, 0);
                        Food ginger = new Food("Имбирь", "", FoodCategory.VEGETABLES, 80, 1.8, 0.8, 15.8);
                        Food granaPadano = new Food("Сыр Грана Падано", "Galbani", FoodCategory.MILK_PRODUCTS, 398, 33, 29, 0);
                        Food granaMoravia = new Food("Сыр Грана Моравия", "Excellent", FoodCategory.MILK_PRODUCTS, 378, 31, 28, 0.6);
                        Food granolaCaramelWithStrawberryAndCherry = new Food("Гранола карамельная с клубникой и вишней", "Вкусвилл", FoodCategory.GROCERIES, 352.4, 9, 6.4, 64.7);
                        Food granolaRaspberryCherryAndCranberry = new Food("Гранола с клубникой, вишней и клюквой", "Самокат", FoodCategory.GROCERIES, 362.4, 9.4, 9.3, 60.3);
                        Food granolaWithBerries = new Food("Гранола ягодная", "Из Лавки", FoodCategory.GROCERIES, 355.2, 9.6, 10.6, 59.1);
                        Food granolaBerries = new Food("Гранола Ягодная", "Жизнь Март", FoodCategory.GROCERIES, 317, 9, 9.6, 51.8);
                        Food granolaWithChocolateAndMelon = new Food("Гранола с шоколадом и дыней", "Жизнь Март", FoodCategory.GROCERIES, 360, 10.4, 9.7, 61);
                        Food granolaWithDriedPlumAndDate = new Food("Гранола с черносливом и финиками", "Древо Жизни", FoodCategory.GROCERIES, 333, 6.5, 11, 52);
                        Food grapefruit = new Food("Грейпфрут", "", FoodCategory.FRUIT, 45, 0.8, 0.2, 10);
                        Food greens = new Food("Микрозелень гороха, подсолнечника, проросщенный маш", "Микрозелень", FoodCategory.VEGETABLES, 25, 3.3, 1.6, 5.4);
                        Food honey = new Food("Мед", "", FoodCategory.GROCERIES, 328, 0.8, 0, 80.3);
                        Food horseradish = new Food("Хрен Столовый", "Махеев", FoodCategory.GROCERIES, 51, 2.5, 2.5, 4.6);
                        Food jalapenoCanned = new Food("Халапеньо маринованный", "Дядя Ваня", FoodCategory.GROCERIES, 30, 0.5, 0.3, 7);
                        Food jamPeachMango = new Food("Джем Персик и Манго", "Махеев", FoodCategory.GROCERIES, 272, 0, 0, 68);
                        Food juiceApple = new Food("Яблочный сок", "Rich", FoodCategory.DRINKS, 42, 0, 0, 10.5);
                        Food kiwi = new Food("Киви", "", FoodCategory.FRUIT, 47, 0.8, 0.4, 8.1);
                        Food kefir = new Food("Кефир", "Першинский", FoodCategory.MILK_PRODUCTS, 57, 3, 3.2, 4);
                        Food kefir2 = new Food("Кефир Ирбитский", "Ирбитский", FoodCategory.MILK_PRODUCTS, 37, 3, 1, 4);
                        Food kvas = new Food("Квас", "Камышловский", FoodCategory.DRINKS, 21, 0.75, 0, 4.4);
                        Food mangoCubes = new Food("Манго кубики жевательные", "Махачонок", FoodCategory.GROCERIES, 320, 0.5, 0.04, 79); // 1 pcs = 10 g
                        Food mangoWithSugar = new Food("Манго, протертое с сахаром", "Махеев", FoodCategory.GROCERIES, 220, 0, 0, 55);
                        Food melon = new Food("Дыня Колхозница", "", FoodCategory.FRUIT, 33, 0.6, 0.3, 7.4);
                        Food milk2_5 = new Food("Молоко 2,5 %", "Домик в деревне", FoodCategory.MILK_PRODUCTS, 53, 3, 2.5, 4.7);
                        Food milk3_2 = new Food("Молоко 3,2 %", "Полевское", FoodCategory.MILK_PRODUCTS, 60, 3, 3.2, 4.7);
                        Food milkChocolateWithCookie = new Food("Молочный шоколад с печеньем", "KitKat", FoodCategory.SWEETS, 528, 6, 29.2, 59.3);
                        Food milkVeg = new Food("Овсяный напиток", "Село Зеленое", FoodCategory.DRINKS, 37, 0.8, 1.8, 4.5);
                        Food mozzarella = new Food("Моцарелла для пиццы", "Bonfesto", FoodCategory.MILK_PRODUCTS, 309, 24.5, 23.5, 0);
                        Food mozzarellaDiBufala = new Food("Моцарелла Буффало", "Galbani", FoodCategory.MILK_PRODUCTS, 251, 18, 19, 2);
                        Food mushrooms = new Food("Шампиньоны", "", FoodCategory.GROCERIES, 22, 2.5, 0.1, 3.7);
                        Food mustard = new Food("Горчица", "", FoodCategory.GROCERIES, 217, 8, 10, 24);
                        Food mustardBavarian = new Food("Горчица баварская", "Самокат", FoodCategory.GROCERIES, 170, 8, 10, 11);
                        Food mustardRussian = new Food("Горчица русская", "Махеев", FoodCategory.GROCERIES, 196, 7.5, 9.5, 20);
                        Food mustardDijon = new Food("Горчица зерненая", "Махеев", FoodCategory.GROCERIES, 218, 8, 10, 24);
                        Food nectarine = new Food("Нектарины", "", FoodCategory.FRUIT, 44, 1.1, 0.3, 9);
                        Food oatmeals = new Food("Овсянные хлопья долгой варки", "", FoodCategory.GROCERIES, 355, 13, 7, 60);
                        Food oliveOil = new Food("Оливковое масло", "Filippo Berio", FoodCategory.MILK_PRODUCTS, 898, 0, 99.8, 0);
                        Food olives = new Food("Маслины", "Bonduelle", FoodCategory.STONE_FRUIT, 115, 0.8, 10.6, 6.2);
                        Food olives2 = new Food("Маслины каламата с косточкой", "Dolce Albero", FoodCategory.STONE_FRUIT, 222, 1, 22, 3.6);
                        Food olives3 = new Food("Пряные оливки с прованскими травами", "Gustaria", FoodCategory.STONE_FRUIT, 183, 1.2, 19, 0);
                        Food olives4 = new Food("Оливки Каламон с косточкой", "Metro Chef", FoodCategory.STONE_FRUIT, 259, 1.6, 27, 0.5); // 1 pcs = 7.5 g
                        Food onion = new Food("Лук репчатый", "", FoodCategory.VEGETABLES, 41, 1.4, 0.2, 8.2);
                        Food orange = new Food("Апельсин", "", FoodCategory.FRUIT, 43, 0.9, 0.2, 8.1);
                        Food orangeJam = new Food("Джем апельсиновый", "Махеев", FoodCategory.GROCERIES, 200, 0, 0, 50);
                        Food oysterSauce = new Food("Устричный соус", "Pan-Asian Club", FoodCategory.GROCERIES, 150, 0.5, 0, 37);
                        Food pancakeMix = new Food("Смесь для выпечки оладьев", "Печем дома", FoodCategory.PASTRIES, 360, 9.5, 2, 76);
                        Food pasta = new Food("Макароны", "Barilla", FoodCategory.GROCERIES, 359, 14, 2, 69.7);
                        Food parmesan = new Food("Пармезан", "", FoodCategory.MILK_PRODUCTS, 392, 35.8, 25, 3.2);
                        Food parsley = new Food("Петрушка", "", FoodCategory.VEGETABLES, 49, 3.7, 0.4, 7.6);
                        Food peaGrean = new Food("Микрозелень гороха", "", FoodCategory.GREENS, 23, 3, 0.6, 1.4);
                        Food pear = new Food("Груша Конференция", "", FoodCategory.FRUIT, 47, 0.4, 0.3, 10.3);
                        Food pesto = new Food("Соус песто", "Monti", FoodCategory.GROCERIES, 481, 6.9, 48, 3.8);
                        Food plumYellow = new Food("Желтая слива", "", FoodCategory.FRUIT, 50, 0.8, 0.3, 10);
                        Food pollack = new Food("Минтай", "Русская рыбная компания", FoodCategory.FISH, 75, 16, 1, 0);
                        Food porkEscalope = new Food("Свиной эскалоп", "Мираторг", FoodCategory.MEAT, 200, 17, 15, 0);
                        Food porkLeg = new Food("Свиной окорок", "Слово мясника", FoodCategory.MEAT, 170, 16, 12, 0);
                        Food potato = new Food("Картофель сырой", "", FoodCategory.VEGETABLES, 77, 2, 0.4, 16.3);
                        Food prianikWIthCurds = new Food("Пряники творожные", "Жизнь Март", FoodCategory.PASTRIES, 358, 5.5, 6.1, 74.4);
                        Food psyllium = new Food("Псиллиум", "Нармак", FoodCategory.GROCERIES, 42, 2.9, 0.1, 7.3);
                        Food puffPastry = new Food("Тесто слоеное бездрожжевое", "Талосто", FoodCategory.PASTRIES, 350, 5, 21, 36);
                        Food pumpkinSeeds = new Food("Тыквенные семечки", "", FoodCategory.NUTS, 590, 30, 49, 8);
                        Food radish = new Food("Редис", "", FoodCategory.NUTS, 20, 1.2, 0.1, 3.4);
                        Food raspberry = new Food("Малина", "", FoodCategory.BERRY, 46, 0.8, 0.5, 8.3);
                        Food riceFlour = new Food("Мука рисовая", "Макфа", FoodCategory.GROCERIES, 359, 7.1, 0.9, 79.5);
                        Food riceLongGrain = new Food("Длиннозерный рис", "Самокат", FoodCategory.GROCERIES, 340, 7, 0.4, 77);
                        Food rocket = new Food("Рукола", "Самокат", FoodCategory.VEGETABLES, 25, 2.6, 0.7, 2.1);
                        Food sausages = new Food("Сосиски молочные", "Черкашин", FoodCategory.MEAT, 246, 12, 22, 0);
                        Food sauceMustard = new Food("Соус горчичный", "Heinz", FoodCategory.GROCERIES, 380, 1.5, 34, 16);
                        Food scalop = new Food("Гребешки", "", FoodCategory.FISH, 69, 12.1, 0.5, 3.2);
                        Food semolina = new Food("Манная крупа", "Самокат", FoodCategory.GROCERIES, 330, 10, 1, 71);
                        Food shrimp = new Food("Креветки", "Камарон", FoodCategory.SEAFRUIT, 90.9, 20.7, 0.9, 0);
                        Food silverSalmon = new Food("Кижуч", "Лента", FoodCategory.FISH, 160, 21, 7.6, 0);
                        Food skyr = new Food("Напиток кисломолочный Скир", "Exponenta", FoodCategory.MILK_PRODUCTS, 35, 6, 0, 3);
                        Food soySauce = new Food("Соевый соус", "Самокат", FoodCategory.GROCERIES, 55, 3.5, 0, 10);
                        Food strawberry = new Food("Клубника", "Домашняя", FoodCategory.BERRY, 32, 0.7, 0.3, 5.7);
                        Food sugar = new Food("Сахар", "", FoodCategory.GROCERIES, 399, 0, 0, 99.8);
                        Food sushka = new Food("Сушки ванильные", "Жизнь Март", FoodCategory.PASTRIES, 370, 8.5, 5.5, 72); // 1 pcs = 6 g
                        Food sweetPotato = new Food("Батат", "", FoodCategory.VEGETABLES, 86, 1.6, 0.1, 20);
                        Food tartine = new Food("Тартин", "Анна Шеина", FoodCategory.PASTRIES, 187, 6, 0.8, 38);
                        Food tartineWithCranberry = new Food("Тартин с клюквой", "Анна Шеина", FoodCategory.PASTRIES, 188, 6, 0.8, 39);
                        Food teriakiSauce = new Food("Соус терияки", "Самокат", FoodCategory.GROCERIES, 150, 3, 0, 35);
                        Food tilsiter = new Food("Сыр Тильзитер", "Село зеленое", FoodCategory.GROCERIES, 335, 23, 27, 0);
                        Food tomato = new Food("Помидор", "", FoodCategory.VEGETABLES, 24, 1.1, 0.2, 3.8);
                        Food tomatoMinced = new Food("Томаты измельченные с луком и чесноком", "Dolce Albero", FoodCategory.VEGETABLES, 39, 1.6, 0.5, 6.2);
                        Food tomatoSauce = new Food("Томаты измельченные", "Dolce Albero", FoodCategory.VEGETABLES, 28, 1.2, 0.5, 5);
                        Food tortilla = new Food("Лепешка мексиканская", "Delicados", FoodCategory.PASTRIES, 286, 7, 7.7, 47.2);
                        Food turkeyBreastFilet = new Food("Филе грудки индейки охлажденное", "Индилайт", FoodCategory.POULTRY, 100, 20, 2.5, 0);
                        Food turkeyFilet = new Food("Филе индейки", "Индилайт", FoodCategory.POULTRY, 131.2, 20.2, 5.6, 0);
                        Food turkeyFiletSmall = new Food("Филе индейки малое", "Индилайт", FoodCategory.POULTRY, 110, 22, 2.5, 0);
                        Food turkeyHam = new Food("Ветчина из филе индейки", "Индилайт", FoodCategory.POULTRY, 100, 16, 2, 4);
                        Food turkeyFilletGrilled = new Food("Филе из грудки индейки копчено-вареное на гриле", "Индилайт", FoodCategory.POULTRY, 100, 16, 2, 4);
                        Food turkeyFilletMarble = new Food("Филе индейки мраморное", "Индилайт", FoodCategory.POULTRY, 110, 14, 4, 4); // 120 g
                        Food turkeyThighFillet = new Food("Филе бедра индейки", "Индилайт", FoodCategory.POULTRY, 150, 19, 8, 0);
                        Food dumplingsWithCurds = new Food("Варенники с обезжиренным творогом", "Самокат", FoodCategory.READY, 200, 10, 4, 32);
                        Food vegOil = new Food("Растительное масло", "Слобода", FoodCategory.GROCERIES, 899, 0, 99.9, 0);
                        Food walnut = new Food("Грецкий орех", "Твердый знак", FoodCategory.STONE_FRUIT, 660, 16, 61, 11);
                        Food yeast = new Food("Дрожжи сухие", "Айдиго", FoodCategory.GROCERIES, 420, 49, 6, 40);
                        Food yoghurtForDrinkingWithoutFat = new Food("Питьевой йогурт обезжиренный", "Exponenta", FoodCategory.MILK_PRODUCTS, 60, 12, 0, 2.5);
                        Food yoghurtWithoutFat = new Food("Йогурт обезжиренный", "Exponenta", FoodCategory.MILK_PRODUCTS, 62, 12.5, 0, 3);
                        Food yoghurtGreek1 = new Food("Йогурт греческий", "Teos", FoodCategory.MILK_PRODUCTS, 66.8, 8, 2, 4.2);
                        Food yoghurtGreek2 = new Food("Греческий йогурт", "Простоквашино", FoodCategory.MILK_PRODUCTS, 63, 8.7, 2, 2.6);
                        Food yoghurtWithGranolaCherryAndCinnamon = new Food("Йогурт с гранолой вишня и корица", "Жизнь Март", FoodCategory.MILK_PRODUCTS, 140, 3.9, 4.7, 20.4); // 180 g
                        Food zucchini = new Food("Цукини", "", FoodCategory.VEGETABLES, 17, 1.2, 0.3, 2.1);
                        """;
                        
        String regex = "new Food\\(\\s*\"([^\"]+)\"\\s*,\\s*\"([^\"]+)\"\\s*,\\s*FoodCategory\\.(\\w+)\\s*,\\s*([\\d.]+)\\s*,\\s*([\\d.]+)\\s*,\\s*([\\d.]+)\\s*,\\s*([\\d.]+)\\s*\\)";
        Pattern pattern = Pattern.compile(regex);

        // Получаем текущее время для полей created_at и updated_at
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = now.format(formatter);

        // Имя итогового файла (сохранится в корне проекта)
        String fileName = "init.sql";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            String[] lines = rawData.split("\n");
            int counter = 0;

            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String name = matcher.group(1);
                    String brand = matcher.group(2);
                    String category = matcher.group(3);
                    String calories = matcher.group(4);
                    String proteins = matcher.group(5);
                    String fats = matcher.group(6);
                    String carbohydrates = matcher.group(7);

                    // Если марка "", превращаем её в NULL для базы данных, иначе экранируем кавычками
                    String brandValue = brand.isEmpty() ? "NULL" : "'" + brand + "'";

                    // Собираем полноценный SQL INSERT
                    String sql = String.format(
                            "INSERT INTO foods (user_id, name, brand, food_category, calories, proteins, fats, carbohydrates, is_favorite, created_at, updated_at) " +
                                    "VALUES (NULL, '%s', %s, '%s', %s, %s, %s, %s, false, '%s', NULL);",
                            name, brandValue, category, calories, proteins, fats, carbohydrates, currentTime
                    );

                    writer.write(sql);
                    writer.newLine();
                    counter++;
                }
            }
            System.out.println("Успешно! Сгенерировано " + counter + " запросов в файл " + fileName);

        } catch (IOException e) {
            System.err.println("Ошибка при записи файла: " + e.getMessage());
        }
    }
}
