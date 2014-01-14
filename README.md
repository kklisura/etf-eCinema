etf-eCinema
===========

Academic project on Faculty of Electrical Engineering, Sarajevo 

Team:
  - Kenan Klisura https://github.com/kklisura
  - Naida Huseinovic https://github.com/nhuseinovic
  - Maida Kapic https://github.com/maidakapic
  - Tarik Kaldzija https://github.com/ttare

Goal: Develop netflix-like system with the following features:
  - content streaming
  - cinema seat reaservation
  - content magement
  - user management


Running:

    git clone ..
    (start MySQL server 5.7; import /database/dump.sql by any means)
    cd etf-eCinema
    mvn clean compile tomcat7:run
    (navigate to http://localhost:8080/eCinema in browser)

Frameworks and technologies used:
  - Project management: Maven 3.x
  - DB: MySQL server 5.6
  - Backend:
    - Jeresy 1.7
    - Tomcat 7.5 web server
  - Frontend:
    - Ember.js 1.2.0
    - Bootstrap
  

Frontpage image:
![Frontpage image](/images/front.png)


LICENSE
-------
CRAPL

http://matt.might.net/articles/crapl/
