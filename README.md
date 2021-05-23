# RESTful-API-usercreation
API REST de creación de usuarios

SpringBoot 2.5 -
BD H2 Embedded -
Tomcat Embedded -
Java 11 -
Graddle -
JWT

Endpoint POST http://localhost:8080/createuser

Base de datos http://localhost:8080/h2-console/
User: "test"
Password: "test"

![image](https://user-images.githubusercontent.com/69010141/119248218-a9cbf200-bb5d-11eb-98c9-b78a0c3fd634.png)



*Script de creación de la BD H2, esta se genera de forma automática al ejecutar la aplicación

    create table phone (
       id binary not null,
        citycode varchar(25),
        countrycode varchar(25),
        number varchar(15),
        id_users binary,
        primary key (id)
    )

    create table user (
       id binary not null,
        created timestamp not null,
        email varchar(100) not null,
        enable boolean not null,
        last_login timestamp not null,
        modified timestamp not null,
        name varchar(100) not null,
        password varchar(100) not null,
        token varchar(500),
        primary key (id)
    )

    alter table phone 
       add constraint FK8m1wylwgsdd2lqhu9gyj74hm2 
       foreign key (id_users) 
       references user

Diagrama

![image](https://user-images.githubusercontent.com/69010141/119248137-31fdc780-bb5d-11eb-8ede-2b8742fb1767.png)


Prueba en Postman

![image](https://user-images.githubusercontent.com/69010141/119248612-58713200-bb60-11eb-934a-0eec2a1e39d8.png)


{
    "name": "Marcelo",
    "email": "marcelo@test.cl",
    "password": "wsswWW22w",
    "phones": [
        {
            "number": "123456",
            "citycode": "test",
            "countrycode": "test"
        }
    ]
}



  
