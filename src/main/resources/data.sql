INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT (name, address)
VALUES ('Monument', 'Trg Republic, 1'),
       ('Walter', 'Zaplanska, 32');

INSERT INTO DISH (name, price, restaurant_id, date)
VALUES ('Grilled Salmon', 1200, 1, '2023-04-24'),
       ('Caesar Salad', 500, 1, '2023-04-24'),
       ('Chicken Alfredo', 400, 1, '2023-04-24'),
       ('Margherita Pizza', 800, 1, '2023-04-24'),
       ('Beef Tacos', 750, 1, '2023-04-24');
INSERT INTO DISH (name, price, restaurant_id)
VALUES ('Sushi Roll', 1000, 2),
       ('Pad Thai', 600, 2),
       ('Spaghetti Carbonara', 450, 2),
       ('Pho Soup', 300, 2),
       ('Miso Ramen', 400, 2);
