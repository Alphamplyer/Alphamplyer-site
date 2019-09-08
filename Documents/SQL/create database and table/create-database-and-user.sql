CREATE USER admin WITH
	LOGIN
	SUPERUSER
	CREATEDB
	CREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1;
	


CREATE DATABASE "alphamplyer-microservice-news"
    WITH 
    OWNER = admin
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE DATABASE "alphamplyer-microservice-news-test"
    WITH 
    OWNER = admin
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE DATABASE "alphamplyer-microservice-orders"
    WITH 
    OWNER = admin
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE DATABASE "alphamplyer-microservice-orders-test"
    WITH 
    OWNER = admin
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
	
CREATE DATABASE "alphamplyer-microservice-products"
    WITH 
    OWNER = admin
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE DATABASE "alphamplyer-microservice-products-test"
    WITH 
    OWNER = admin
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE DATABASE "alphamplyer-microservice-users"
    WITH 
    OWNER = admin
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE DATABASE "alphamplyer-microservice-users-test"
    WITH 
    OWNER = admin
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE DATABASE "alphamplyer-microservice-users-permissions"
    WITH 
    OWNER = admin
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE DATABASE "alphamplyer-microservice-users-permissions-test"
    WITH 
    OWNER = admin
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;