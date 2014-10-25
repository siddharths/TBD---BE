### What is this?
Repository for the backend of a project that is yet to be named. We'll name it soon, relax. Patience is a virtue.

### How do I muck around with the source?
I recommend using Eclipse. Clone the repo, open up a terminal/command prompt navigate to your working directory and paste this `./gradlew clean eclipse`

Boom! You have a ready to use eclipse project. Just import it into eclipse and you're good to go.

### How do I run it?
This is a web app that provides RESTful endpoints. There are a few steps that you need to follow to get the app up and running for the first time.

#### 1. Configure it
You will need an instance of MySQL running somewhere with the user and a database setup and configure the `src/main/resources/application.properties` 
so that the app knows how to reach the MySQL instance. If you have just downloaded the source code, you may not see the application.properties file. 
There is a sample file provided - `sample/application.properties` that you can copy to the resources folder and configure as per your needs. If you cannot configure that file, 
you probably shouldn't be reading this.

#### 2. Run it
About time, eh? Run the following command in your terminal/command prompt - `./gradlew clean bootRun`. If you are using your IDE, just run it like any other Java app.

#### 3. Access it
Access one of the endpoints to see if it works through your browser. **TODO:** Add a couple of trial URLs. 