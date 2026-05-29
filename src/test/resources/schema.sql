CREATE TABLE food
(
    id   BIGSERIAL,
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(255),
    food_category VARCHAR(255),
    user_id BIGINT,
    calories double precision,
    proteins double precision,
    fats double precision,
    carbohydrates double precision,
    created timestamp without time zone,
    updated timestamp without time zone
);