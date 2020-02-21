# Nootbook server with Java / Spring Boot
simple notebook server that can execute pieces of code in an interpreter using Spring Boot technology
* Preserve the state of the variables
* Allow multi-sessions and multi-interpreters

## Background
Interactive notebooks are experiencing a rise in popularity. Notebooks offer an environment for Data scientists to comfortably share research, collaborate with others and explore and visualize data. The data usually comes from executable code that can be written in the client (e.g. Python, SQL) and is sent to the server for execution. Popular notebook technologies which this approach are Apache Zeppelin (https://zeppelin.apache.org/  ) and Jupyter Notebooks (  http://jupyter.org/ ).

## Installation
- Build the project:  `$ mvn clean install -DskipTests`
- Run the jar file: `$ java -jar target/notebook-server-spring-boot-0.0.1-SNAPSHOT.jar`
  
## Usage 
 - The `/execute` endpoint that accepts a JSON object with this format:
  ```json
  {
    "code": "%[interpreterName][whitespace][code]",
    "sessionId": "string"
  }
  ```
  
 - returns a json object. The object has the following format 
  ```json
  {
    "result":  "string"
  }
 ```
  - if there is an exception, the response has the following format : 
 ```json
 {
    "code":  "string",
    "message":  "string",
    "details":  "string"
  }
  ````
