CREATE TABLE t_user
(
  id   NUMBER(8) ,
  name VARCHAR2(16) NOT NULL ,
  password VARCHAR2(32) NOT NULL ,
  CONSTRAINT table_name_id_name_pk PRIMARY KEY (id)
);

CREATE SEQUENCE seq_user_id
START WITH 1
INCREMENT BY 1
NOCYCLE ;

CREATE OR REPLACE TRIGGER tri_user_id
BEFORE INSERT ON t_user
FOR EACH ROW
WHEN (new.id IS NULL )
BEGIN
SELECT seq_user_id.nextval INTO :new.id FROM dual;
END;
