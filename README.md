# quest-student-registration
1. checkout quest-student-registration
2. follow below steps to start kafka zookeepr and broker if it is not done.
  a. download kafka from official website and extract it in local folder if it is not done already.
  b. Navagate to kafka bin folder and open command prompt and then start zookeepr using below command if it is not done.
 zookeeper-server-start.bat ..\\..\config\zookeeper.properties
 
 c. Navagate to kafka bin folder and open command prompt and then start kafka broker using below command if it is not done.
 kafka-server-start.bat ..\..\config\server.properties
 
4. start quest-student-registration. it is spring boot application. 
5. use attached postman collections to test api. there are three apis:
  a. create/register student. it is post api
  b. get student based on student id. it is get api
  c. get all students based on class. it is get api.
  
 
6. DB Configuration:
  a. I had some issuse for my-sql db installation in local. so used sql server db.
  b. change db connection details as per your local db in application.properties
  c. I have also attached db script for design of Student and Address table for your referrence. 
  

