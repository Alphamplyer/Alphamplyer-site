# Spring Boot - Microservice Architecture (VF)

Ce projet sera "open source" dès que j'aurais terminé la première version. Quand il sera open source, j'espère que des personnes viendrons donner les idées, améliorer et parfaire cette application. Je n'ai qu'un an de formation, mais je compte bien me servir de ce projet pour m'améliorer dans ce domaine.

## But du projet

<i>Les documents concernant le projet seront bientôt disponibles une fois terminé</i>

Concernant le but du projet est simple. Faire un site pour un jeux vidéo. Aujourd'hui les jeu vidéo prennent de plus en plus de place dans le monde du développement mais les ressources concernant ces derniers sur le web manque un peu. Très peu de design, d'exemple de base de données, ...
La création de jeux vidéo me passionnant, j'ai donc opté pour une idée. Faire un site sur le jeu vidéo, dont la conception sera avancée et complète. 

C'est donc, ce qui m'amène à ce projet :

- 4 sites d'exemple fonctionnant avec un ensemble de microservices.
  - Un Site Principale (d'accueil) : Conçu pour afficher les dernières actualités, créer un compte, télécharger les jeux de la firme, ...
  - Un Site Boutique : Conçu pour vendre différent objets relatif aux jeux et autre produit dérivé. Le tout comprenant un système de réduction.
  - Un Site Forum : Conçu pour que les joueurs puissent discuté, débattre de sujet autour de l'univers des jeux, par exemple.
  - Un Site SAV et Support : Conçu pour répondre aux problèmes qu'un utilisateur peu rencontrer.


## Technologies Used

Voici les technologies actuellement utilisées dans :

### Les Microservices

- Web :
  - Spring Web Starter
- SQL :
  - JDBC API
  - PostgreSQL Driver
Developer Tools :
  - Spring Boot DevTools

### Les sites

- Web :
  - Spring Web Starter
- Template Engines
  - Thymeleaf
- Spring Cloud Routing
  - OpenFeign
Developer Tools :
  - Spring Boot DevTools
  
  
  
Plus tard, je tenterai sûrement de protéger les microservices avec un API Gateway. Pour ça je pense que j'utiliserai les technologies suivantes :

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
- Spring Cloud Security (Le fonctionnement de la sécurité avec ces dépendances reste encore obscure pour moi.):
  - Cloud Security
  - Cloud OAuth2

## Compte Super Utilisateur pour les tests :

ID : Administrateur
password : admin

## Information : Avancement du projet

Pour l'instant toutes les fonctionnalité ne sont pas terminé et la pluspart seront fini dans la version 2
