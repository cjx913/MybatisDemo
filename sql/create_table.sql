-- user table
CREATE TABLE t_user
(
  user_id   NUMBER(8) ,
  name VARCHAR2(16) NOT NULL ,
  password VARCHAR2(32) NOT NULL ,
  CONSTRAINT user_id_pk PRIMARY KEY (user_id)
);

CREATE SEQUENCE seq_user_id
START WITH 1
INCREMENT BY 1
NOCYCLE ;

CREATE OR REPLACE TRIGGER tri_user_id
BEFORE INSERT ON t_user
FOR EACH ROW
WHEN (new.user_id IS NULL )
BEGIN
SELECT seq_user_id.nextval INTO :new.user_id FROM dual;
END;


-- message table
CREATE TABLE t_message (
  message_id      NUMBER(8),
  gender VARCHAR2 (4),
  e_mail  VARCHAR2(128),
  age     NUMBER(3),
  birth   DATE,
  user_id NUMBER(8) NOT NULL ,
  CONSTRAINT message_id_pk PRIMARY KEY (message_id),
  CONSTRAINT message_USER_ID_fk FOREIGN KEY (user_id) REFERENCES T_USER (user_id),
  CONSTRAINT message_gender_ck CHECK (gender IN ('男','女'))
);

CREATE SEQUENCE seq_message_id
START WITH 1
INCREMENT BY 1
NOCYCLE ;

CREATE OR REPLACE TRIGGER tri_message_id
BEFORE INSERT ON t_message
FOR EACH ROW
WHEN (new.message_id IS NULL )
BEGIN
SELECT seq_message_id.nextval INTO :new.message_id FROM dual;
END;

--   order table
CREATE TABLE t_order(
  order_id NUMBER(8),
  total number(8,2),
  remark VARCHAR2(128),
  user_id NUMBER(8) NOT NULL,
  CONSTRAINT order_id_pk PRIMARY KEY (order_id),
  CONSTRAINT order_user_id_fk FOREIGN KEY (user_id) REFERENCES t_user(user_id)
);

CREATE SEQUENCE seq_order_id
START WITH 1
INCREMENT BY 1
NOCYCLE ;

CREATE OR REPLACE TRIGGER tri_order_id
BEFORE INSERT ON t_order
FOR EACH ROW
WHEN (new.order_id IS NULL )
BEGIN
SELECT seq_order_id.nextval INTO :new.order_id FROM dual;
END;

-- product table
CREATE TABLE t_product(
  product_id NUMBER(8),
  product_name VARCHAR2(64) NOT NULL ,
  product_price NUMBER(8,2) NOT NULL ,
  product_picture VARCHAR2(128),
  product_remark VARCHAR2(128),
  CONSTRAINT product_id_pk PRIMARY KEY (product_id)
);

CREATE SEQUENCE seq_product_id
START WITH 1
INCREMENT BY 1
NOCYCLE ;

CREATE OR REPLACE TRIGGER tri_product_id
BEFORE INSERT ON t_product
FOR EACH ROW
WHEN (new.product_id IS NULL )
BEGIN
SELECT seq_product_id.nextval INTO :new.product_id FROM dual;
END;