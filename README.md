Liferay Angular Portlet
=======================

A simple Liferay portlet which uses Angular (>= 2) to execute REST calls and display data.
The frontend content is delivered with thymeleaf templates.

It makes use of the [frontend-maven-plugin](https://github.com/eirslett/frontend-maven-plugin) to download npm and node and install all necessary dependencies.
The Angular app is built with angular-cli which takes care of typescript compilation and test support.
(The JavaScript sources get bundled with webpack)

### Build & Deploy

With *mvn clean install* you can build the portlet. 
After successfully executing the maven command you can find the *.war* file in the corresponding target folder.
Deploy the *.war* file in your Liferay portal and you can see the portlet in action.

### Run Tests

The example comes with predefined npm scripts to execute frontend unit tests (written with jasmine, executed with karma).
Just run *npm test* and the tests will be executed.
The tests will be executed automatically when you run *mvn clean install* to ensure that the build is stable.
