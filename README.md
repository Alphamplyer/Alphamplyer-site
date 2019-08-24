# Spring Boot - Microservice Architecture

<i>This document has been translated by Google Translate. If something is wrong, or poorly worded, tell me what needs to be changed.</i>

This project, will be an open-source project, as soon as it is ready in this first version. Once it is open source, I hope there will be people to give ideas, improve and perfect this application. I only have one year of training, but I'm counting on this project to learn more about it.


## Project Goal

<i>Project development documents will be available soon once completed and translated</i>

Regarding the purpose of the project is simple. Make a site for a video game. Today video games are taking more and more space in the world of development but the resources concerning these on the web are a bit lacking. Very little design, sample database, ...
The creation of video games fascinates me, so I opted for an idea. Make a site on the video game, whose design will be advanced and complete.

So, that brings me to this project:

- 4 example sites operating with a set of microservices.
  - A Main Site (Home): Designed to display the latest news, create an account, download the games of the firm, ...
  - Shop Site: Designed to sell different game related items and other derivative products. All including a reduction system.
  - Forum Site: Designed for players to discuss, discuss topics around the world of games, for example.
  - A Service and Support Site: Designed to respond to problems that a user may encounter.


## Used Technologies

Here are the technologies currently used in:

### Microservices

- Web:
  - Spring Web Starter
- SQL:
  - JDBC API
  - PostgreSQL Driver
- Developer Tools:
  - Spring Boot DevTools

### The sites

- Web:
  - Spring Web Starter
- Template Engines:
  - Thymeleaf
- Spring Cloud Routing:
  - OpenFeign
- Developer Tools:
  - Spring Boot DevTools

## Unused Technologies

Later, I will surely try to protect microservices with a Gateway API. For that I think that I will use the following technologies:

- Spring Cloud Routing:
  - Zuul
  - Ribbon
- Spring Cloud Discovery:
  - Eureka Discovery Client
  - Eureka Server
- Spring Cloud Tracing:
  - Sleuth
  - Zipkin Client
- Spring Cloud Config:
  - Config Client
  - Config Server
- Spring Cloud Security (The operation of security with these dependencies is still obscure to me):
  - Cloud Security
  - Cloud OAuth2

## Information : Projet Progress

I'm planning the end of the first version in one to two weeks.
