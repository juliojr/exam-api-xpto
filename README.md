# exam-api-xpto

O desafio Back-End é uma api desenvolvida com Spring e banco de dados Postgres
Esta dentro da pasta api-cities-xpto

A avaliação de Banco de Dados PL SQL, esta dentro da pasta "plsql" com os arquivos conforme solicitado.

As respostas corretas do arquivo "Avaliação Completa PLSQL" estão destacadas em verde claro;
Os Scripts da part2 estão escritos em Azul e tambem dentro do aquivo script_plsql_part2.sql


#api-cities-xpto

testes locais

http://localhost:8080/swagger-ui.html#/city-controller

RETURN TOKEN 

POST http://localhost:8080/oauth/token

AUTHORIZATION Basic Auth

BODY
KEY grant_type, VALUE password
KEY username, VALUE tester
KEY password, VALUE 123

retorno do token, use a token do "access_token" com o tipo bearer
{
    "access_token": "5cd09fea-9bce-4ec6-a5f7-cd45d4ca4aad",
    "token_type": "bearer",
    "refresh_token": "3b0c7d81-22eb-49c8-a21d-7368da4b3cbb",
    "expires_in": 43199,
    "scope": "password"
}
TODAS AS REQUESTS VÃO UTILIZAR O MESMO PADRAO
DO TIPO Bearer Token  "access_token"

POST http://localhost:8080/upload
Bearer Token  "access_token"
KEY file VALUE .csv

GET http://localhost:8080/capital
Bearer Token  "access_token"

GET http://localhost:8080/largest-smallest-ufs
Bearer Token  "access_token"

GET http://localhost:8080/number-cities-uf
Bearer Token  "access_token"

GET http://localhost:8080/city/{ibgeId}
Bearer Token  "access_token"

GET http://localhost:8080/uf/{uf}
Bearer Token  "access_token"


POST http://localhost:8080/city
Bearer Token  "access_token"
BODY
{
	"ibgeId": 3501152,
	"uf": "SP",
	"name": "Alumnio Teste",
	"capital": "",
	"lon": -47.2590569185,
	"lat": -47.2590569185,
	"noAccents": "Aluminio",
	"alternativeNames": "",
	"microregion": "Sorocaba",
	"mesoregion": "teste teste"
}

DELETE http://localhost:8080/city/{ibgeId}
Bearer Token  "access_token"

GET http://localhost:8080/filter
Bearer Token  "access_token"

GET http://localhost:8080/filter/{column}
Bearer Token  "access_token"

GET http://localhost:8080/total-records
Bearer Token  "access_token"

GET http://localhost:8080/greater-distance
Bearer Token  "access_token"












