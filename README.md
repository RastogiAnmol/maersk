# maersk
Movie API demonstration using Spring for Maersk interview

This project was created by intellij idea

In order to run this, please import this project using intellij or any Java IDE and then install all the maven dependencies

Once the project is setup and the dependencies are downloaded then go to MovieServiceApplication.java and run the spring boot application.

Please view the screenshots and hit the endpoints inorder to fetch the data. This project uses H2 as inmemory DB and the data is seeded once you start
the application

This project runs on port 8080

Following endpoints can be used to test the application. Please check the attached screenshots for simulating the API request


Example endpoints:-

Get endpoint
==============
http://localhost:8080/movie/

http://localhost:8080/movie/1990/year

http://localhost:8080/movie/9/ratings

Put endpoint
=============

http://localhost:8080/movie/

please use this as body 

{
    "id": 12,
    "name": "A new movie updated",
    "year": "2020-01-02",
    "rating": 5
}


Post endpoint
=============

http://localhost:8080/movie/

body

{
    "name": "A new movie",
    "year": "2020-01-02",
    "rating": 5
}

Please test the exception thrown by typing any random URL

for example
===========
http://localhost:8080/movie/9/fdsfsdf
