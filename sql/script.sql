DROP TABLE IF EXISTS CARTVALIDATION;
DROP TABLE IF EXISTS BURGLARY;
DROP TABLE IF EXISTS EMPOWERMENT;
DROP TABLE IF EXISTS DECLARATION;
DROP TABLE IF EXISTS CITIZEN;
DROP TABLE IF EXISTS TOKEN;
DROP TABLE IF EXISTS DIGITALL_USER;
DROP TABLE IF EXISTS MINISTRY;
CREATE TABLE MINISTRY
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(100),
    state int
);
INSERT INTO MINISTRY
VALUES (default, 'Ministère de la défense nationale', 1);
INSERT INTO MINISTRY
VALUES (default, 'Ministère de l éducation nationale ', 1);
INSERT INTO MINISTRY
VALUES (default, 'Ministère du transport', 1);
CREATE TABLE DIGITALL_USER
(
    id        SERIAL PRIMARY KEY,
    login     VARCHAR(50),
    password  TEXT,
    name      VARCHAR(100),
    firstName VARCHAR(100),
    role VARCHAR (100),
    ministry INT,
    CONSTRAINT fk_ministry_user
        FOREIGN KEY (ministry)
            REFERENCES MINISTRY (id)
);
INSERT INTO DIGITALL_USER VALUES (default ,'tsiory@defense.mg','1e3e84f0a3bfea349a18ff6e2dccfb3866b9d11f','Andrianarisaona','Tsiory ','Patrouilleur de nuit',1);
INSERT INTO DIGITALL_USER VALUES (default ,'tsiory@men.mg','1e3e84f0a3bfea349a18ff6e2dccfb3866b9d11f','Andrianarisaona','Tsiory ','Verificateur de diplôme',2);
INSERT INTO DIGITALL_USER VALUES (default ,'tsiory@mttm.mg','1e3e84f0a3bfea349a18ff6e2dccfb3866b9d11f','Andrianarisaona','Tsiory ','Police de la route',3);
CREATE TABLE TOKEN
(
    id            SERIAL PRIMARY KEY,
    digitall_user INT,
    token         TEXT,
    validity      TIMESTAMP,
    state         INT,
    CONSTRAINT fk_user_token
        FOREIGN KEY (digitall_user)
            REFERENCES DIGITALL_USER (id)
);
CREATE TABLE CITIZEN(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    firstName VARCHAR (100),
    picture TEXT,
    birthday DATE,
    sex INT,
    qrCode TEXT,
    phone VARCHAR(30)
);
INSERT INTO CITIZEN VALUES (default ,'Andrianaivoravelona','Sandy Lovanirina Jonathan','','1998-11-27',1,'333F0662D395FBCA621F63769E469FC4665010C4','261346969433');
INSERT INTO CITIZEN VALUES (default ,'Andrianarisaona','Misaina Tsiory Tafita','','1999-08-21',1,'E9AC5601CEDD8747FDD7C911329F9A0BA8B78A19','261346969433');
INSERT INTO CITIZEN VALUES (default ,'Rakozotozanany','Mialy','','1998-07-14',0,'BBD60495FC36A9FE28159B64EB815F9CD24EC0B0','261346969433');
CREATE TABLE DECLARATION(
    id SERIAL PRIMARY KEY,
    citizen INT,
    title TEXT,
    content TEXT,
    longitude float,
    latitude float ,
    digitall_user INT,
    ministry INT,
    date_declaration TIMESTAMP,
    state INT,
    CONSTRAINT fk_citizen_declaration FOREIGN KEY (citizen) REFERENCES CITIZEN(id),
    CONSTRAINT fk_user_declaration FOREIGN KEY (digitall_user) REFERENCES DIGITALL_USER(id),
    CONSTRAINT fk_ministry_declaration FOREIGN KEY (ministry) REFERENCES MINISTRY(id)
);
CREATE TABLE EMPOWERMENT(
    id SERIAL PRIMARY KEY,
    value TEXT,
    ministry INT,
    state INT,
    CONSTRAINT fk_empowerment_ministry FOREIGN KEY(ministry) REFERENCES  MINISTRY(id)
);
INSERT INTO EMPOWERMENT VALUES (default ,'Déclaration de vol',1,1);
INSERT INTO EMPOWERMENT VALUES (default ,'Bagarre de rue',1,1);
--data of empowerment
CREATE TABLE BURGLARY(
    id SERIAL PRIMARY KEY,
    date_burglary TIMESTAMP,
    longitude float,
    latitude float,
    place VARCHAR (100),
    description TEXT,
    digitall_user int,
    CONSTRAINT fk_burglary_declaration FOREIGN KEY (digitall_user) REFERENCES DIGITALL_USER(id)
);
CREATE TABLE CARTVALIDATION(
    id SERIAL PRIMARY KEY,
    citizen int,
    date_validation TIMESTAMP,
    code INT,
    state int ,
    CONSTRAINT fk_validation_citizen FOREIGN KEY (citizen) REFERENCES CITIZEN(id)
);
