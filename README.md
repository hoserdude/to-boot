
# Spring Boot AngularJS MongoDB PostgresSQL OpenId TodoMVC
* Simple <a href="http://projects.spring.io/spring-boot/">Spring Boot</a> application with <a href="http://www.mongodb.org/">MongoDB</a>.

# Heroku Setup info

Procfile
```
web: java $JAVA_OPTS -jar target/to-boot-1.0.0.jar
```

A one time config change was made to enable MongoDB and the Spring config activation:

```
heroku addons:add mongohq
heroku config:set JAVA_OPTS='-Xmx384m -Xss512k -XX:+UseCompressedOops -Dserver.port=$PORT -Dspring.profiles.active=heroku'
```
# Getting started
After cloning this repo:
```
git remote add heroku git@heroku.com:to-boot.git
```
You can now deploy to heroku if you have permissions.  Make sure to download the heroku Toolbelt on your system.

# Deployment Stuff

The app will run on your localhost using the local mongodb instance, but when deployed to Heroku, the "heroku" profile kicks in (thanks to the config setting above) and it uses MongoHQ.  Locally, you can run it from an IDE (Intellij, Eclipse) as an application (Application.java), you don't have to deploy it to a web container.

To re-deploy fresh code, stop the proc, push and start it:

```
heroku ps:scale web=0
git push heroku master
heroku ps:scale web=1
```
#Usage

## Web

* http://toboot.herokuapp.com/
 
## API

See http://toboot.herokuapp.com/api for the swagger UI
