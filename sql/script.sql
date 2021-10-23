DROP TABLE IF EXISTS PERMIS;
DROP TABLE IF EXISTS FOLDER_DOCUMENT;
DROP TABLE IF EXISTS FOLDER;
DROP TABLE IF EXISTS DOCUMENT;
DROP TABLE IF EXISTS NIVEAU;
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
    phone VARCHAR(30),
    fokontany VARCHAR (100),
    commune VARCHAR (100),
    address TEXT
);
INSERT INTO CITIZEN VALUES (default ,'Andrianaivoravelona','Sandy Lovanirina Jonathan','','1998-11-27',1,'333F0662D395FBCA621F63769E469FC4665010C4','261346969433','FIATA','TANA REV','Lot VC 5 Ambanidia');
INSERT INTO CITIZEN VALUES (default ,'Andrianarisaona','Misaina Tsiory Tafita','','1999-08-21',1,'E9AC5601CEDD8747FDD7C911329F9A0BA8B78A19','261346969433','Ambanidia-Miandrarivo','TANA REV','Lot IBM 25 Bis Tsaralalana');
INSERT INTO CITIZEN VALUES (default ,'Rakozotozanany','Mialy','','1998-03-31',0,'BBD60495FC36A9FE28159B64EB815F9CD24EC0B0','261346969433','Androndrakely-SaropodyAntonta','TANA REV','Lot 130 67Ha Sud');
INSERT INTO CITIZEN VALUES (default ,'Ramanantsoa','Anjara','','1997-04-30',0,'BBD60495FC36A9FE28159B64EB815F9CD24EC0B047','261346969433','Androndrakely-SaropodyAntonta','TANA REV','2S 44 Anjanahary');
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
CREATE TABLE NIVEAU(
    id SERIAL PRIMARY KEY,
    name VARCHAR (100),
    photo VARCHAR (50),
    type VARCHAR (50)
);
INSERT INTO NIVEAU values (default , 'Fokontany','group','font-awesome');
INSERT INTO NIVEAU values (default , 'Commune','home','antdesign');
INSERT INTO NIVEAU values (default , 'Tribunal','hammer','font-awesome-5');
INSERT INTO NIVEAU values (default , 'Office du Bacc','cast-education','material-community-icons');

CREATE TABLE DOCUMENT (
    id SERIAL PRIMARY KEY,
    name VARCHAR (100),
    validity INT,
    niveau INT,
    CONSTRAINT fk_document_niveau FOREIGN KEY (niveau) REFERENCES NIVEAU(id)
);

INSERT INTO DOCUMENT VALUES (default ,'Certificat de résidence',90,1);
INSERT INTO DOCUMENT VALUES (default ,'Certificat de vie',90,1);
INSERT INTO DOCUMENT VALUES (default ,'Acte de naissance',90,2);
INSERT INTO DOCUMENT VALUES (default ,'Acte de naissance traduit',90,3);
INSERT INTO DOCUMENT VALUES (default ,'Bulletin numéro 3',-1,3);
INSERT INTO DOCUMENT VALUES (default ,'Diplôme bacc',-1,4);
INSERT INTO DOCUMENT VALUES (default ,'Relevé de note Bacc',-1,4);

CREATE TABLE FOLDER(
    id SERIAL PRIMARY KEY,
    name VARCHAR (100),
    citizen int,
    CONSTRAINT fk_folder_citizen FOREIGN KEY (citizen) REFERENCES CITIZEN(id)
);
INSERT INTO FOLDER VALUES (default ,'Document passeport',1);
INSERT INTO FOLDER VALUES (default ,'Document inscription université',1);

CREATE TABLE FOLDER_DOCUMENT(
  id SERIAL PRIMARY KEY,
  folder INT,
  document INT,
  CONSTRAINT fk_folder_FOLDER_DOCUMENT FOREIGN KEY (folder) REFERENCES FOLDER(id),
  CONSTRAINT fk_document_FOLDER_DOCUMENT FOREIGN KEY (document) REFERENCES DOCUMENT(id)
);
INSERT INTO  FOLDER_DOCUMENT VALUES (default ,1,1);
INSERT INTO  FOLDER_DOCUMENT VALUES (default ,1,3);
select DOCUMENT.*  from DOCUMENT left join FOLDER_DOCUMENT FD on DOCUMENT.id = FD.document WHERE FD.folder = 1;
CREATE TABLE PERMIS(
    id SERIAL PRIMARY KEY,
    numero VARCHAR (100),
    delivrance DATE,
    place VARCHAR (100),
    a INT ,
    ap INT,
    b INT,
    c INT,
    d INT,
    e INT,
    f INT,
    citizen INT ,
    CONSTRAINT fk_permis_permiscitizen FOREIGN KEY (citizen) REFERENCES CITIZEN(id)
);

INSERT INTO PERMIS VALUES (default ,'0545099T','2015-07-01','Antananarivo',0,0,1,0,0,0,0,1);
INSERT INTO PERMIS VALUES (default ,'0545099T','2017-06-04','Antananarivo',0,0,1,0,0,0,0,2);
INSERT INTO PERMIS VALUES (default ,'0545099T','2018-01-07','Antananarivo',0,0,1,0,0,0,0,3);