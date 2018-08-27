CREATE TABLE exame_nf
             (
                          idnf         NUMBER(10) NOT NULL,
                          numero       NUMBER NOT NULL,
                          datacadastro DATE NOT NULL,
                          totalgeral   NUMERIC(15,2) NOT NULL
             );ALTER TABLE exame_nf ADD ( CONSTRAINT exame_nf_pk PRIMARY KEY ( idnf ) );CREATE SEQUENCE exame_nf_seq START WITH 1;CREATE
OR
replace TRIGGER bi_exame_nf BEFORE
INSERT
ON exame_nf FOR EACH ROW WHEN (
              NEW.idnf IS NULL ) BEGIN
SELECT exame_nf_seq.NEXTVAL
INTO   :new.idnf
FROM   dual;

END;
CREATE TABLE exame_itemnf
             (
                          iditemnf  NUMBER(10) NOT NULL,
                          idnf      NUMBER(10) NOT NULL,
                          idproduto NUMBER(10) NOT NULL,
                          qtde      NUMERIC(15,2) NOT NULL,
                          valor     NUMERIC(15,2) NOT NULL
             );

ALTER TABLE exame_itemnf ADD ( CONSTRAINT exame_itemnf_pk PRIMARY KEY ( iditemnf ) );

ALTER TABLE exame_itemnf ADD ( CONSTRAINT exame_itemnf_fk_nk FOREIGN KEY ( idnf ) REFERENCES exame_nf ( idnf ) );

CREATE SEQUENCE exame_itemnf_seq START WITH 1;
CREATE
OR
replace TRIGGER bi_exame_itemnf BEFORE
INSERT
ON exame_itemnf FOR EACH ROW WHEN (
              NEW.iditemnf IS NULL ) BEGIN
SELECT exame_itemnf_seq.NEXTVAL
INTO   :new.iditemnf
FROM   dual;

END;