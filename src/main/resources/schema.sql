DROP TABLE IF EXISTS EMPLOYEE;
CREATE TABLE EMPLOYEE (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  AGE INT NOT NULL,
  DEPARTMENT VARCHAR(250) ,
  DESCRIPTION VARCHAR(250) ,
  INFORMATION VARCHAR(250) ,
  FULL_NAME VARCHAR(250) ,
  START_TIME TIMESTAMP);