CREATE TABLE Room (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    locale VARCHAR (3),
    room_name VARCHAR(15) UNIQUE,
    price INT ,
    capacity INT,
    room_class VARCHAR(10)
    );