
# Babysitter Kata Project

This project focuses on a simple payment calculator for a babysitter working and getting paid for one night. 
Link on Heroku:- https://whispering-refuge-96521.herokuapp.com/

# Background
This kata simulates a babysitter working and getting paid for one night. The rules are pretty straight forward.

The babysitter:

* starts no earlier than 5:00PM
* leaves no later than 4:00AM
* gets paid $12/hour from start-time to bedtime
* gets paid $8/hour from bedtime to midnight
* gets paid $16/hour from midnight to end of job
* gets paid for full hours (no fractional hours)


### Prerequisites

* eclipse and intellij or some sorte of IDE
* Tomcat(v9) plugin is already on the pom file but you have to do the configration. Follow the link https://crunchify.com/step-by-step-guide-to-setup-and-install-apache-tomcat-server-in-eclipse-development-environment-ide/

All the packages and third party Libray are in the pom file so no need download any thing.

### How To Run
 1, Link on Heroku:- https://whispering-refuge-96521.herokuapp.com/
 
 2, you can run the Application in your chosen IDE, e.g. IntelliJ
 * clone the repostery first.
 * Make sure you configure  the Tomcat server version 9.0
 * Imported  the project in to chosen IDE. 
 * Right right click on the Projet from the package explorer
 * Run as > run on server > Finsh > Ok
 * That will open a web page with http://localhost:8080/Babysitter/

3 Alternatively,  CLI and If you have maven installed in your matchin,
Make sure to be in the project root directory that contain pom file.
* run the command:
>mvn projectname
This will create and install all the dependency and It clean and build the project,
* run the command:
> mvn install
This will also generate a jar file with all the dependencies which we will run once it has been created. 

### Important files

`Pom.xml`

    This file includes all of the dependency and third party libraries.  
## Authors


* **Abrham Fantaye**
