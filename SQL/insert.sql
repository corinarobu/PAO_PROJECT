-- Secvența pentru tabelul User
create sequence user_sequence
start with 1
increment by 1
minvalue 1
nocycle;

-- Secvența pentru tabelul Client
create sequence client_sequence
start with 1
increment by 1
minvalue 1
nocycle;

-- Secvența pentru tabelul Employee
create sequence employee_sequence
start with 1
increment by 1
minvalue 1
nocycle;

-- Secvența pentru tabelul Drink
create sequence drink_sequence
start with 1
increment by 1
minvalue 1
nocycle;

-- Secvența pentru tabelul Food
create sequence food_sequence
start with 1
increment by 1
minvalue 1
nocycle;

-- Secvența pentru tabelul Order
create sequence order_sequence
start with 1
increment by 1
minvalue 1
nocycle;

-- Secvența pentru tabelul Restaurant
create sequence restaurant_sequence
start with 1
increment by 1
minvalue 1
nocycle;

-- Secvența pentru tabelul Review
create sequence review_sequence
start with 1
increment by 1
minvalue 1
nocycle;




-- Pentru tabelul User
insert into "user"(id, username, email, password, age, phoneNumber)
values (user_sequence.nextval, 'User1', 'user1.com', 'user1_123', 21, '0711111111');

insert into "user"(id, username, email, password, age, phoneNumber)
values (user_sequence.nextval, 'User2', 'user2.com', 'user2_123', 21, '0722222222');


insert into "user"(id, username, email, password, age, phoneNumber)
values (user_sequence.nextval, 'User3', 'user3.com', 'user3_123', 21, '0733333333');

insert into "user"(id, username, email, password, age, phoneNumber)
values (user_sequence.nextval, 'User4', 'user4.com', 'user4_123', 21, '0744444444');

insert into "user"(id, username, email, password, age, phoneNumber)
values (user_sequence.nextval, 'User5', 'user5.com', 'user5_123', 21, '0755555555');

insert into "user"(id, username, email, password, age, phoneNumber)
values (user_sequence.nextval, 'User6', 'user6.com', 'user6_123', 21, '0766666666');

insert into "user"(id, username, email, password, age, phoneNumber)
values (user_sequence.nextval, 'User7', 'user7.com', 'user7_123', 21, '0777777777');

insert into "user"(id, username, email, password, age, phoneNumber)
values (user_sequence.nextval, 'User8', 'user8.com', 'user8_123', 21, '0788888888');

insert into "user"(id, username, email, password, age, phoneNumber)
values (user_sequence.nextval, 'User9', 'user9.com', 'user9_123', 21, '0799999999');

insert into "user"(id, username, email, password, age, phoneNumber)
values (user_sequence.nextval, 'User10', 'user10.com', 'user10_123', 21, '0700000000');




-- Pentru tabelul Client
insert into client(id, address)
values (client_sequence.nextval, 'adresa1');

insert into client(id, address)
values (client_sequence.nextval, 'adresa2');

insert into client(id, address)
values (client_sequence.nextval, 'adresa3');

insert into client(id, address)
values (client_sequence.nextval, 'adresa4');

insert into client(id, address)
values (client_sequence.nextval, 'adresa5');

insert into client(id, address)
values (client_sequence.nextval, 'adresa6');

insert into client(id, address)
values (client_sequence.nextval, 'adresa7');

insert into client(id, address)
values (client_sequence.nextval, 'adresa8');

insert into client(id, address)
values (client_sequence.nextval, 'adresa9');

insert into client(id, address)
values (client_sequence.nextval, 'adresa10');


-- Pentru tabelul Employee
insert into Employee(id, jobTitle, hiringDate, salary, dailyWorkHours)
values (employee_sequence.nextval, 'jobTitle1', '01-01-2010', 100, 1);

insert into Employee(id, jobTitle, hiringDate, salary, dailyWorkHours)
values (employee_sequence.nextval, 'jobTitle2', '02-02-2010', 200, 2);

insert into Employee(id, jobTitle, hiringDate, salary, dailyWorkHours)
values (employee_sequence.nextval, 'jobTitle3', '03-03-2010',300, 3);

insert into Employee(id, jobTitle, hiringDate, salary, dailyWorkHours)
values (employee_sequence.nextval, 'jobTitle4', '04-04-2010', 400, 4);

insert into Employee(id, jobTitle, hiringDate, salary, dailyWorkHours)
values (employee_sequence.nextval, 'jobTitle5', '05-05-2010', 500, 5);

insert into Employee(id, jobTitle, hiringDate, salary, dailyWorkHours)
values (employee_sequence.nextval, 'jobTitle6', '06-06-2010', 600, 6);

insert into Employee(id, jobTitle, hiringDate, salary, dailyWorkHours)
values (employee_sequence.nextval, 'jobTitle7', '07-07-2010', 700, 7);

insert into Employee(id, jobTitle, hiringDate, salary, dailyWorkHours)
values (employee_sequence.nextval, 'jobTitle8', '08-08-2010', 800, 8);

insert into Employee(id, jobTitle, hiringDate, salary, dailyWorkHours)
values (employee_sequence.nextval, 'jobTitle9', '09-09-2010', 900, 9);

insert into Employee(id, jobTitle, hiringDate, salary, dailyWorkHours)
values (employee_sequence.nextval, 'jobTitle10', '10-10-2010', 1000, 10);


-- Inserare în tabelul "drink"
INSERT INTO drink(id, name, price, alcoholic, calories)
values(drink_sequence.nextval, 'Nume1', 100, 0, 1);

INSERT INTO drink(id, name, price, alcoholic, calories)
values(drink_sequence.nextval, 'Nume2', 200, 0, 2);

INSERT INTO drink(id, name, price, alcoholic, calories)
values(drink_sequence.nextval, 'Nume3', 300, 0, 3);

INSERT INTO drink(id, name, price, alcoholic, calories)
values(drink_sequence.nextval, 'Nume4', 400, 0, 4);

INSERT INTO drink(id, name, price, alcoholic, calories)
values(drink_sequence.nextval, 'Nume5', 500, 0, 5);

INSERT INTO drink(id, name, price, alcoholic, calories)
values(drink_sequence.nextval, 'Nume6', 600, 0, 6);

INSERT INTO drink(id, name, price, alcoholic, calories)
values(drink_sequence.nextval, 'Nume7', 700, 0, 7);

INSERT INTO drink(id, name, price, alcoholic, calories)
values(drink_sequence.nextval, 'Nume8', 800, 0, 8);

INSERT INTO drink(id, name, price, alcoholic, calories)
values(drink_sequence.nextval, 'Nume9', 900, 0, 9);

INSERT INTO drink(id, name, price, alcoholic, calories)
values(drink_sequence.nextval, 'Nume10', 1000, 0, 10);



-- Inserare în tabelul "food"
INSERT INTO food (id, name, price, vegetarian, calories)
VALUES(food_sequence.nextval, 'Nume1', 100, 0, 1);

INSERT INTO food (id, name, price, vegetarian, calories)
VALUES(food_sequence.nextval, 'Nume2', 200, 0, 2);

INSERT INTO food (id, name, price, vegetarian, calories)
VALUES(food_sequence.nextval, 'Nume3', 300, 0, 3);

INSERT INTO food (id, name, price, vegetarian, calories)
VALUES(food_sequence.nextval, 'Nume4', 400, 0, 4);

INSERT INTO food (id, name, price, vegetarian, calories)
VALUES(food_sequence.nextval, 'Nume5', 500, 0, 5);


-- Inserare în tabelul "order"
INSERT INTO "order" (id, paymentMethod, desiredArrivalTime)
VALUES(order_sequence.nextval, 'Card', '01-01-2010');

INSERT INTO "order" (id, paymentMethod, desiredArrivalTime)
VALUES(order_sequence.nextval, 'Card', '02-02-2010');

INSERT INTO "order" (id, paymentMethod, desiredArrivalTime)
VALUES(order_sequence.nextval, 'Card', '03-03-2010');

INSERT INTO "order" (id, paymentMethod, desiredArrivalTime)
VALUES(order_sequence.nextval, 'Card', '04-04-2010');

INSERT INTO "order" (id, paymentMethod, desiredArrivalTime)
VALUES(order_sequence.nextval, 'Card', '05-05-2010');

-- Inserare în tabelul "restaurant"
INSERT INTO restaurant (id, name, address, establishmentYear, cuisineType, openingHours)
VALUES(restaurant_sequence.nextval, 'Nume1', 'adress1', 1, 'type1', '1-2');

INSERT INTO restaurant (id, name, address, establishmentYear, cuisineType, openingHours)
VALUES(restaurant_sequence.nextval, 'Nume2', 'adress2', 2, 'type2', '2-3');

INSERT INTO restaurant (id, name, address, establishmentYear, cuisineType, openingHours)
VALUES(restaurant_sequence.nextval, 'Nume3', 'adress3', 3, 'type3', '3-4');

INSERT INTO restaurant (id, name, address, establishmentYear, cuisineType, openingHours)
VALUES(restaurant_sequence.nextval, 'Nume4', 'adress4', 4, 'type4', '4-5');

INSERT INTO restaurant (id, name, address, establishmentYear, cuisineType, openingHours)
VALUES(restaurant_sequence.nextval, 'Nume5', 'adress5', 5, 'type5', '5-6');



-- Inserare în tabelul "review"
INSERT INTO review (id, rating, "comment", userAge)
VALUES(review_sequence.nextval, 1, 'Comment1', 1);

INSERT INTO review (id, rating, "comment", userAge)
VALUES(review_sequence.nextval, 2, 'Comment2', 2);

INSERT INTO review (id, rating, "comment", userAge)
VALUES(review_sequence.nextval, 3, 'Comment3', 3);

INSERT INTO review (id, rating, "comment", userAge)
VALUES(review_sequence.nextval, 4, 'Comment4', 4);

INSERT INTO review (id, rating, "comment", userAge)
VALUES(review_sequence.nextval, 5, 'Comment5', 5);


-- Inserare în tabelul Order-Food
INSERT INTO Order_Food (order_id, food_id) VALUES (1, 1);

INSERT INTO Order_Food (order_id, food_id) VALUES (2, 2);

INSERT INTO Order_Food (order_id, food_id) VALUES (3, 3);

INSERT INTO Order_Food (order_id, food_id) VALUES (4, 4);

INSERT INTO Order_Food (order_id, food_id) VALUES (5, 5);

-- Inserare în tabelul Order-Drink
INSERT INTO ORDER_DRINK (order_id, DRINK_ID) VALUES (1, 1);

INSERT INTO ORDER_DRINK (order_id, DRINK_ID) VALUES (2, 2);

INSERT INTO ORDER_DRINK (order_id, DRINK_ID) VALUES (3, 3);

INSERT INTO ORDER_DRINK (order_id, DRINK_ID) VALUES (4, 4);

INSERT INTO ORDER_DRINK (order_id, DRINK_ID) VALUES (5, 5);

