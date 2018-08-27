--Crie uma consulta SELECT que exiba o total de linhas da tabela EXAME_NF por dia. 
--Exiba somente as linhas que possuam ao menos um item (EXAME_ITEMNF) com valor inferior a 50. 
--Salve a consulta no arquivo SCRIPT4.SQL.
EXPLAIN PLAN FOR
SELECT nf.datacadastro,
       Count(nf.idnf)
FROM   exame_nf nf
WHERE  nf.idnf IN (SELECT idnf
                   FROM   exame_itemnf
                   WHERE  valor < 50)
GROUP  BY nf.datacadastro 
ORDER  BY nf.datacadastro;
SELECT PLAN_TABLE_OUTPUT  FROM TABLE(DBMS_XPLAN.DISPLAY);

-- check data if is ok 
--select count(*) as qtdeBydata from exame_nf nf where TRUNC( nf.datacadastro) = TO_DATE( '20/08/2018', 'dd/mm/yyyy' ) 
--and nf.idnf in(select idnf from exame_itemnf where valor < 50);

    

 