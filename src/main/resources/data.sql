INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest'),
       ('User1', 'user1@yandex.ru', '{noop}password'),
       ('User2', 'user2@yandex.ru', '{noop}password'),
       ('User3', 'user3@yandex.ru', '{noop}password'),
       ('User4', 'user4@yandex.ru', '{noop}password'),
       ('User5', 'user5@yandex.ru', '{noop}password'),
       ('User6', 'user6@yandex.ru', '{noop}password'),
       ('User7', 'user7@yandex.ru', '{noop}password'),
       ('User8', 'user8@yandex.ru', '{noop}password');

INSERT INTO USER_ROLE (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT (name, address)
VALUES ('Monument', 'Trg Republic, 1'),
       ('Walter', 'Zaplanska, 32');

INSERT INTO DISH (name, price, restaurant_id)
VALUES ('Grilled Salmon', 1200, 1),
       ('Caesar Salad', 500, 1),
       ('Chicken Alfredo', 400, 1),
       ('Margherita Pizza', 800, 1),
       ('Beef Tacos', 750, 1),
       ('Sushi Roll', 1000, 2),
       ('Pad Thai', 600, 2),
       ('Spaghetti Carbonara', 450, 2),
       ('Pho Soup', 300, 2),
       ('Miso Ramen', 400, 2);
INSERT INTO DISH (name, date, price, restaurant_id)
VALUES ('Double Cheeseburger', '2023-05-10', 500, 1),
       ('Spaghetti Carbonara', '2023-05-10', 990, 1),
       ('Lasagna Bolognese','2023-05-10',1190, 1),
       ('Cabbage Rolls', '2023-05-10', 990, 1),
       ('Steak Frites', '2023-05-10', 1490, 1),
       ('Beef Stroganoff', '2023-05-10', 1290, 2),
       ('Chicken Curry', '2023-05-10', 990, 2),
       ('Wiener Schnitzel', '2023-05-10', 1490, 2),
       ('Vegetable Salad', '2023-05-10', 300, 2),
       ('Greek Salad', '2023-05-10', 600 , 2);
INSERT INTO VOTE (restaurant_id, user_id)
VALUES (1, 1),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (2, 8),
       (2, 9),
       (2, 10),
       (2, 11);
INSERT INTO VOTE (date, time,  user_id, restaurant_id)
VALUES ('2023-05-10', '10:00:00', 1, 1),
       ('2023-05-10', '10:00:00', 4 , 1),
       ('2023-05-10', '10:00:00', 5, 1),
       ('2023-05-10', '10:00:00', 6, 1),
       ('2023-05-10', '10:00:00', 7, 1),
       ('2023-05-10', '10:00:00', 8, 2),
       ('2023-05-10', '10:00:00', 9, 2),
       ('2023-05-10', '10:00:00', 10, 2),
       ('2023-05-10', '10:00:00', 11, 2);