# SprintoAssignment
cleartrip/makeMytrip assignment

This project consists of multiple tests related to https://www.makemytrip.com/ website.

### Prerequisites
Need to have Java and Maven with any IDE in your machine

### How to use
Import the project in IntelliJ editor.
Go to folder src/test/java/resources/testRunner

Run the xml file "makeMyTripSuite.xml"

## Project structure
Hybrid framework with Page object model design pattern.

Added browser selection for chrome, firefox, safari and Edge

### src/main/java : 
It contains the classes with functionalities that drives the test cases.
### src/test/java : 
It contains the test suites.
### src/test/resources  : 
Contains the config file 
### makeMytripSuite.xml : 
It facilitates running the test suites all at once from this file.

### How to Run tests

* Run the testng.xml file as Test

* We can run it as a maven project as well, through `mvn clean test` command 
