# SpringSecurityJwt

## Description
The **SpringSecurityJwt** is a project that show how implement JWT using Spring Security.

## Technologies
The project use the following technologies:

* Java 11
* Apache Maven
* Spring Boot
* Spring Security
* MySQL Server
* Spring Data JPA
* IDE Eclipse

## Database
A demo database must be created by following SQL script.

```
CREATE DATABASE springsecurityjwt DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE springsecurityjwt;

create table permission
( 
  id INTEGER NOT NULL AUTO_INCREMENT,
 `description` VARCHAR(40) NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users
( 
  id INTEGER NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(20) NULL,
  full_name VARCHAR(60) NULL,
  `password` VARCHAR(255) NULL,
  account_non_expired BIT(1) NULL,
  account_non_locked BIT(1) NULL,
  credentials_non_expired BIT(1) NULL,
  enabled BIT(1) NULL,
  PRIMARY KEY (id)
);

CREATE TABLE user_permission
( 
  id_user INTEGER NOT NULL,
  id_permission INTEGER NOT NULL,
  PRIMARY KEY (id_user, id_permission),
  FOREIGN KEY (id_user) REFERENCES users (id),
  FOREIGN KEY (id_permission) REFERENCES permission (id)
);

INSERT INTO permission (description) VALUES ('ADMIN'), ('MANAGER'), ('COMMON_USER');

INSERT INTO users (user_name, full_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES ('admin', 'Administrator', '$2a$16$9qr2tv0HmXbHBsx.TZFjfux742wCZM32a8Wi6iBqwIqaizlHPuxHS', b'1', b'1', b'1', b'1');

INSERT INTO `user_permission` (`id_user`, `id_permission`) VALUES (1, 1), (2, 1), (1, 2);
```

## Testing the project
**Step 1:** Start the application

**Step 2:** Login in the application 

```
curl \
--location --request POST 'http://localhost:8080/auth/signin' \
--header 'Content-Type: application/json' \
--data-raw '{
  "username": "admin",
  "password": "admin123"
}'
```

The result of above command will be a token in JWT format.

```
{
  "username": "admin",
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJST0xFX01BTkFHRVIifSx7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTYyMTE3Mjk3MiwiZXhwIjoxNjIxMTc2NTcyfQ.Mfl8MepvEqw5Mod6YVho8cIdo7ZiI3_3sdhxN-DQ3S8"
}
```

You can see token details using the link [JWT.io](https://jwt.io/)

**Step 3:** Request Hello endpoint

```
curl \
--location --request GET 'http://localhost:8080/hello' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJST0xFX01BTkFHRVIifSx7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTYyMTE3Mjk3MiwiZXhwIjoxNjIxMTc2NTcyfQ.Mfl8MepvEqw5Mod6YVho8cIdo7ZiI3_3sdhxN-DQ3S8'
```

## Release History
* 1.0.0 (2021-05-16)
    * First version