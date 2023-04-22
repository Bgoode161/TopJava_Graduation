INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT (name, address)
VALUES ('MONUMENT', 'Trg Republic, 1');

INSERT INTO DISH (name, price, restaurant_id)
VALUES ('Grilled Salmon', 1200, 1),
       ('Caesar Salad', 500, 1),
       ('Chicken Alfredo', 400, 1),
       ('Margherita Pizza', 800, 1),
       ('Beef Tacos', 750, 1),
       ('Sushi Roll', 1000, 1),
       ('Pad Thai', 600, 1),
       ('Spaghetti Carbonara', 450, 1),
       ('Pho Soup', 300, 1),
       ('Miso Ramen', 400, 1);
