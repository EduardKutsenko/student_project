DROP TABLE IF EXISTS cr_address_person;
DROP TABLE IF EXISTS cr_person;
DROP TABLE IF EXISTS cr_address;
DROP TABLE IF EXISTS cr_street;
DROP TABLE IF EXISTS cr_district;

CREATE TABLE cr_district(
    district_code integer not null,
	district_name varchar(300),
	PRIMARY KEY (district_code)
);

INSERT INTO cr_district(district_code, district_name)
VALUES(1, 'Viborgskiy');

CREATE TABLE cr_street(
    street_code integer not null,
	street_name varchar(300),
	PRIMARY KEY (street_code)
);

INSERT INTO cr_street(street_code, street_name)
VALUES(1, 'Samsoniy prosp');

CREATE TABLE cr_address(
    address_id SERIAL,
    district_code integer not null,
	street_code integer not null,
	building varchar(10) not null,
    extention varchar(10),
    apartment varchar(10),
	PRIMARY KEY (address_id),
    FOREIGN KEY(district_code) REFERENCES cr_district(district_code) ON DELETE RESTRICT,
    FOREIGN KEY(street_code) REFERENCES cr_street(street_code) ON DELETE RESTRICT
);

INSERT INTO cr_address(district_code, street_code, building, extention, apartment)
VALUES(1, 1, '3', 'EX', '4657A'),
(1, 1, '3', null, '4');


CREATE TABLE cr_person(
     person_id SERIAL,
     sur_name varchar(100) not null,
	 given_name varchar(100) not null,
	 patronimic_name varchar(100) not null,
	 date_of_birth date not null,
	 passport_seria varchar(10),
	 passport_number varchar(10),
	 passport_date date,
	 certificate_number varchar(10),
     certificate_date date,
     PRIMARY KEY(person_id)
);

INSERT INTO cr_person(sur_name, given_name, patronimic_name, date_of_birth, passport_seria, passport_number,
passport_date, certificate_number, certificate_date)
VALUES('Semenov', 'Pavel', 'Trifonovich', '1995-03-09', '123', '535235234', '2010-09-09', null, null),
('Semenova', 'Polina', 'Borisobna', '1996-03-09', '126', 'AA8977', '2013-01-09', null, null),
('Semenova', 'Irina', 'Pavlovna', '2015-06-09', null, null, null, 'RA3434', '2015-06-19'),
('Semenov', 'Igor', 'Pavlovich', '2019-08-05', null, null, null, 'RB999', '2019-08-15');


CREATE TABLE cr_address_person(
    person_address_id SERIAL,
    address_id integer not null,
    person_id integer not null,
    start_date date not null,
    end_date date,
    temporal boolean DEFAULT false,
    PRIMARY KEY(person_address_id),
    FOREIGN KEY(address_id) REFERENCES cr_address(address_id) ON DELETE RESTRICT,
    FOREIGN KEY(person_id) REFERENCES cr_person(person_id) ON DELETE RESTRICT
);

INSERT INTO cr_address_person(address_id, person_id, start_date, end_date, temporal)
VALUES
      (1, 1, '2014-10-12', null, false),
      (2, 2, '2014-10-12', null, true),
      (1, 3, '2016-02-05', null, false),
      (1, 4, '2017-07-15', null, false);


