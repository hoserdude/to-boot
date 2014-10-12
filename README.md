[![Build Status](https://travis-ci.org/hoserdude/to-boot.svg?branch=master)](https://travis-ci.org/hoserdude/to-boot)

# Spring Boot AngularJS MongoDB PostgresSQL OpenId TodoMVC
* [Spring Boot](http://projects.spring.io/spring-boot) server-side application with [MongoDB](http://www.mongodb.org) and [PostgresSQL](http://www.postgresql.org) persistence. Fronted by an AngularJS+RequireJS version of [TodoMVC](http://todomvc.com) that has a remote persistence service (rather than local storage).  Also includes Spring-Security-OpenId based on [Rob Winch's Boot demo](https://github.com/rwinch/spring-boot-openid) for a demonstration of how to manage User accounts in such a scenario.  Bonus inclusion of [Swagger](http://swagger.io) leveraging [Swagger-SpringMVC](https://github.com/martypitt/swagger-springmvc) to demo how to document an API.

# Contribute!
* If you have ideas on how to make this better, pull requests are welcome!
* If you can fix bugs, all the better!

# Known issues
See [Issues List](https://github.com/hoserdude/to-boot/issues)

# Local Setup Info
To-Boot requires:
* PostgresSQL
* MongoDB

Install those two services with your favorite package managers or directly from the source.

Life is easy if you setup Postgres to have username: postgres and password: password or you can edit the `application.yml` in the src/resources directory

Locally, you can run it from an IDE (Intellij, Eclipse) as an application (`Application.java`), you don't have to deploy it to a web container.
 
# Heroku Setup Info

Procfile
```
web: java $JAVA_OPTS -Dserver.port=$PORT -jar target/to-boot-1.0.0.jar
```
Postgres is available by default with Java apps.

A one time config change was made to enable MongoDB and the Spring config activation:

```
heroku addons:add mongohq
heroku config:set JAVA_OPTS='-Xmx384m -Xss512k -XX:+UseCompressedOops -Dspring.profiles.active=heroku'
```
# Getting started
After cloning this repo:
```
git remote add heroku git@heroku.com:to-boot.git
```
You can now deploy to heroku if you have permissions.  Make sure to download the heroku Toolbelt on your system.

# Deployment Stuff

The app will run on your localhost using the local mongodb instance, but when deployed to Heroku, the "heroku" profile kicks in (thanks to the config setting above) and it uses MongoHQ. 

To re-deploy fresh code, stop the proc, push and start it:

```
heroku ps:scale web=0
git push heroku master
heroku ps:scale web=1
```
#Usage

## Web

* http://to-boot.herokuapp.com/
 
## API

See http://to-boot.herokuapp.com/api for the swagger UI
