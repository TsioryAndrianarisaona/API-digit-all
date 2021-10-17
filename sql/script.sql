DROP TABLE IF EXISTS  DIGITALL_USER;
CREATE TABLE DIGITALL_USER(
    id SERIAL PRIMARY KEY,
    login VARCHAR(50),
    password TEXT,
    name VARCHAR(100),
    firstName VARCHAR(100),
    telephone VARCHAR(100),
    email VARCHAR (100),
    birthday DATE,
    isAdmin INT
);
INSERT  INTO DIGITALL_USER VALUES (default ,'tsiory','1e3e84f0a3bfea349a18ff6e2dccfb3866b9d11f','Andrianarisaona','Misaina Tsiory Tafita','+261346969433','tsioryrams@gmail.com','1999-08-21',1);

DROP TABLE IF EXISTS TOKEN;

CREATE TABLE TOKEN(
                      id SERIAL PRIMARY KEY,
                      digitall_user INT,
                      token TEXT,
                      validity TIMESTAMP,
                      state INT,
                      CONSTRAINT fk_user
                          FOREIGN KEY (digitall_user)
                              REFERENCES DIGITALL_USER(id)
);
