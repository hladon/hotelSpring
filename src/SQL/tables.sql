CREATE TABLE Room (
    room_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    room_name_en VARCHAR(15) UNIQUE,
    room_name_ua VARCHAR(15) UNIQUE,
    price INT ,
    capacity INT,
    category VARCHAR(15)
    );

    CREATE TABLE userDB (
    user_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_name VARCHAR(25) UNIQUE,
    password VARCHAR(100),
    role VARCHAR (10),
    active BOOLEAN
    );
    CREATE TYPE reservation_status AS ENUM ('BOOKED', 'RESERVED', 'NOT_AVAILABLE');
    CREATE TYPE room_class AS ENUM ('STANDARD','DELUXE','SUITE');

    CREATE TABLE reservation(
    reservation_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY ,
    fk_user_id INT,
    CONSTRAINT user_fk FOREIGN KEY (fk_user_id) REFERENCES userDB(user_id) ,
    fk_room_id INT,
    CONSTRAINT room_fk FOREIGN KEY (fk_room_id) REFERENCES Room(room_id) ,
    start_rent DATE ,
    end_rent DATE ,
    capacity INT,
    status reservation_status
    );

SELECT room.*,res.status FROM room left join (select * from reservation where start_rent< '2021-02-13'
							  and end_rent>'2021-02-12') as res
							  ON room.id=res.room_id order by res.status desc limit 3 offset 3;