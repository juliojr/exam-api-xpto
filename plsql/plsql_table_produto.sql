CREATE TABLE PRODUTO(
  IDPRODUTO NUMBER(10) NOT NULL,
  CODIGOINTERNO  VARCHAR2(60)  NOT NULL,
  DESCR  VARCHAR2(120)  NOT NULL,
  ATIVO  CHAR(1)  NOT NULL);

ALTER TABLE PRODUTO ADD (
  CONSTRAINT PRODUTO_PK PRIMARY KEY (IDPRODUTO));

CREATE SEQUENCE PRODUTO_SEQ START WITH 1;

CREATE OR REPLACE TRIGGER BI_PRODUTO
BEFORE INSERT ON PRODUTO
FOR EACH ROW
 WHEN (new.IDPRODUTO IS NULL) 
BEGIN
  SELECT PRODUTO_SEQ.NEXTVAL 
  INTO :new.IDPRODUTO
  FROM dual;
END;


INSERT ALL
 INTO PRODUTO (CODIGOINTERNO, DESCR, ATIVO) VALUES ('000001','OLEO DE SOJA','S')
 INTO PRODUTO (CODIGOINTERNO, DESCR, ATIVO) VALUES ('000002','OLEO DE GIRASSOL','S')
 INTO PRODUTO (CODIGOINTERNO, DESCR, ATIVO) VALUES ('000003','TELEFONE SEM FIO','S')
 INTO PRODUTO (CODIGOINTERNO, DESCR, ATIVO) VALUES ('000004','MOUSE MICROSOFT','S')
 INTO PRODUTO (CODIGOINTERNO, DESCR, ATIVO) VALUES ('000005','JOHNNIE WALKER BLUE LABEL','S')
SELECT * FROM dual;

SELECT * FROM PRODUTO;

 

