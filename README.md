[![Build Status](https://travis-ci.org/hoserdude/to-boot.svg?branch=master)](https://travis-ci.org/hoserdude/to-boot)

# Spring Boot AngularJS MongoDB PostgresSQL OpenId TodoMVC
* <a href="http://projects.spring.io/spring-boot/">Spring Boot</a> server-side application with <a href="http://www.mongodb.org/">MongoDB</a> and <a href="http://www.postgresql.org/">PostgresSQL</a> persistence. Fronted by an AngularJS+RequireJS version of <a href"http://todomvc.com">TodoMVC</a> that has a remote persistence service (rather than local storage).  Also includes Spring-Security-OpenId for a demonstration of how to manage User accounts in such a scenario.

# Contribute!
* If you have ideas on how to make this better, pull requests are welcome!
* If you can fix bugs, all the better!

# Known issues
* JS resources are not served from Heroku for some reason, looking into that.
* Editing TODOS seems to get stuck sometimes, seems to be an Angular thing I'm doing wrong.

# Local Setup Info
To-Boot requires:
* PostgresSQL
* MongoDB

Install those two services with your favorite package managers or directly from the source.

Life is easy if you setup Postgres to have username: postgres and password: password or you can edit the application.yml in the src/resources directory

Locally, you can run it from an IDE (Intellij, Eclipse) as an application (Application.java), you don't have to deploy it to a web container.
 
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
