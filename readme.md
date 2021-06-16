#Bem vindo

Para executar este projeto 

Execute a classe principal _**TaskApplication**_ 

Ira iniciar o projeto na porta 5430

Caso se sinta a vontade a usar o postman, esta na pasta _resources/postman_ 
importe no postman e usufrua das colections


Se quiser executar por linha de comando

## Cadastro

```
curl --location --request POST 'localhost:5430/api/auth/signup' \
 --header 'Content-Type: application/json' \
 --data-raw '{
     "username":"user1",
     "password":"senha1",
     "email":"email1@email.com",
     "roles":["user"]
 }'
```

```
curl --location --request POST 'localhost:5430/api/auth/signup' \
 --header 'Content-Type: application/json' \
 --data-raw '{
     "username":"user2",
     "password":"senha2",
     "email":"email2@email.com",
     "roles":["admin"]
 }'
```

## **Logar**

```
curl --location --request POST 'localhost:5430/api/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username":"user1",
    "password":"senha1"
}'
```

###Agora sobre uso do TODO LIST

##Get 
```
curl --location --request GET 'localhost:5430/api/task' \
--header 'Authorization: Bearer [COLOQUE O TOKEN AQUI]'
```
##post
```
curl --location --request POST 'localhost:5430/api/task' \
--header 'Authorization: Bearer [COLOQUE O TOKEN AQUI]' \
--header 'Content-Type: application/json' \
--data-raw '{
    "contentTask":"conteudo",
    "descriptionTask":"descricao",
    "status":"PENDING"
}'
```

##Status
```
curl --location --request GET 'localhost:5430/api/task/status/PENDING' \
--header 'Authorization: Bearer [COLOQUE O TOKEN AQUI]'
```

