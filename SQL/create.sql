-- Creare tabelul "user"
CREATE TABLE App_User (
                        user_id INT PRIMARY KEY,
                        username VARCHAR2(255) NOT NULL,
                        email VARCHAR2(255) NOT NULL,
                        password VARCHAR2(255) NOT NULL,
                        age INT,
                        phoneNumber VARCHAR2(20)
);

CREATE TABLE client (
                        client_id INT PRIMARY KEY,
                        user_id INT,
                        address VARCHAR2(255),
                        CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES App_User(user_id) ON DELETE CASCADE
);

CREATE TABLE employee (
                          employee_id INT PRIMARY KEY,
                          user_id INT,
                          jobTitle VARCHAR2(255),
                          hiringDate DATE,
                          salary NUMBER(10, 2),
                          dailyWorkHours INT,
                          CONSTRAINT fk_employee_id FOREIGN KEY (user_id) REFERENCES App_User(user_id) on delete cascade
);

-- Creare tabelul "drink"
CREATE TABLE drink (
                       id INT PRIMARY KEY,
                       name VARCHAR2(255) NOT NULL,
                       price INT,
                       alcoholic NUMBER(1),
                       calories INT
);


-- Creare tabelul "food"
CREATE TABLE food (
                      id INT PRIMARY KEY,
                      name VARCHAR2(255) NOT NULL,
                      price INT,
                      vegetarian number(1),
                      calories INT
);


-- Creare tabelul "order"
CREATE TABLE "order" (
                         id INT PRIMARY KEY,
                         paymentMethod VARCHAR2(255),
                         desiredArrivalTime VARCHAR2(255)
);


-- Creare tabelul "restaurant"
CREATE TABLE restaurant (
                            id INT PRIMARY KEY,
                            name VARCHAR2(255) NOT NULL,
                            address VARCHAR2(255),
                            establishmentYear INT,
                            cuisineType VARCHAR2(255),
                            openingHours VARCHAR2(255)
);

-- Creare tabelul "review"
CREATE TABLE review (
                        id INT PRIMARY KEY,
                        rating INT,
                        "comment" VARCHAR(255),
                        userAge INT
);



-- Tabela pentru legarea comenzilor de mâncare de comenzile principale
CREATE TABLE Order_Food (
                            order_id INT,
                            food_id INT,
                            FOREIGN KEY (order_id) REFERENCES "order"(id),
                            FOREIGN KEY (food_id) REFERENCES food(id)
);

-- Tabela pentru legarea comenzilor de băutură de comenzile principale
CREATE TABLE Order_Drink (
                             order_id INT,
                             drink_id INT,
                             FOREIGN KEY (order_id) REFERENCES "order"(id),
                             FOREIGN KEY (drink_id) REFERENCES drink(id)
);

