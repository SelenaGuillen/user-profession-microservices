# Mag Mutual User Professions Microservices
## Table of Contents
* [UI Screenshots](#ui-screenshots)
* [API Documentation](#api-documentation)
* [Postman Tests](#postman-tests)
* [AWS RDS Database](#aws-rds-database)
* [Final Thoughts](#final-thoughts)

# AWS Elastic Beanstalk Link
Go to [Home Page](http://professions-env-2.eba-wppceaet.us-east-2.elasticbeanstalk.com/api/users).

![image](https://user-images.githubusercontent.com/31986528/215148862-af234275-a34a-4d08-b925-81d8d3260145.png)

# UI Screenshots
###### Home Page

<img src="https://lh3.googleusercontent.com/RZ_2J6ZE_llDOXVQWpnPB0Q9Bsx-8edMBqXv-vwozNvm40MPAo2McvI1-DbAAXE7QTk=w2400" width="800" height="500">


###### Dropdown Menu 
This dropdown menu is rendered based on the database itself.
Both dropdown menus (profession and country) behave the same way.

<img src="https://lh3.googleusercontent.com/EW52Cqw03G8Nn8pV8tOMEFC8O_65xJ1kz3TdgNmBlawj2WmSZTRCxR-fMQ7i1S4oEUA=w2400" width="800" height="500">

###### User Page
<img src="https://lh4.googleusercontent.com/Hy0Ei8VoJTaL3Brro2rYXoqAbFnIaOU4msODMTqLaBN0SPdpaPVn4wVdDdpRP_malIE=w2400" width="800" height="200">

###### Date Range Page displayed on AWS Elastic Beanstalk
This page will not render based on the params because date is most likely in the wrong format. 
<img src="https://user-images.githubusercontent.com/31986528/215132983-72e908f3-a21f-4f20-a9f2-d92ae5da4c52.png" width="450" height="100">

###### Date Range Page displayed locally
<img src="https://user-images.githubusercontent.com/31986528/215134280-e8d79ed9-ca9a-4e21-b2be-45e2da065b7b.png" width="800" height="250">

###### Users By Profession Page
<img src="https://user-images.githubusercontent.com/31986528/215135680-d0d792bc-ef54-4a4b-8bc5-ed29154c2f83.png" width="800" height="400">

###### Users By Country Page
<img src="https://user-images.githubusercontent.com/31986528/215136023-b2119570-1455-44f1-9d09-1cc017e62b2c.png" width="800" height="400">

###### Sort By Pages
All sort pages use the same template. This is an example of sorting by first name.
<img src="https://user-images.githubusercontent.com/31986528/215136343-5c005328-c890-4686-84ee-fb211cfd2f7f.png" width="800" height="400">

###### Not Found Page whenever a resource is not able to be found. 
<img src="https://user-images.githubusercontent.com/31986528/215136770-3510ada6-f1f7-44a2-bce0-361fce5dfad0.png" width="450" height="100">

# API Documentation
- Home Page: ```/api/users```
###### Required Endpoints that return a ResponseBody
* GET user by id: ```/users/{id}```
* GET user by country: ```/users/country/{country}```
* GET user in date range: ```/users/date/{start}/{end}```
* GET user by profession: ```/users/profession/{profession}```

###### Endpoints that take in RequestParam and return a template
* GET user by id: ```/users/id```
* GET user by country: ```/users/country```
* GET user in date range: ```/users/date```
* GET user by profession: ```/users/profession```

###### Sorting Endpoints
* GET user sort by date ascending: ```/users/sort/date/asc```
* GET user sort by date descending: ```/users/sort/date/desc```
* GET user sort by first name: ```/users/sort/name/first```
* GET user sort by last name: ```/users/sort/name/last```

# Postman Tests
## AWS Elastic Beanstalk
###### GET ```http://professions-env-2.eba-wppceaet.us-east-2.elasticbeanstalk.com/api/users/122```
![image](https://user-images.githubusercontent.com/31986528/215146126-2e5efbe2-2d77-4f9c-a736-4d7640f16c60.png)

###### GET ```http://professions-env-2.eba-wppceaet.us-east-2.elasticbeanstalk.com/api/users/country/Mexico```
![image](https://user-images.githubusercontent.com/31986528/215146254-cb72d0de-006b-4a4e-9a10-183f4649adb9.png)

###### GET ```http://professions-env-2.eba-wppceaet.us-east-2.elasticbeanstalk.com/api/users/date/2020-08-30/2020-09-02```
![image](https://user-images.githubusercontent.com/31986528/215146507-5dbc34aa-f0b0-47ba-a395-40a8231d7a33.png)

###### GET ```http://professions-env-2.eba-wppceaet.us-east-2.elasticbeanstalk.com/api/users/profession/developer```
![image](https://user-images.githubusercontent.com/31986528/215146567-ce157ce8-8a9e-4b29-80b4-9cc3a233ac3c.png)

## Local
###### Only date behaves differently locally because of the date format
###### GET ```http://localhost:5000/api/users/date/2020-08-30/2020-09-02```
![image](https://user-images.githubusercontent.com/31986528/215145875-33a6deca-e5a5-426b-90d8-5bb030690db2.png)

# AWS RDS Database
###### UML Diagram
![image](https://user-images.githubusercontent.com/31986528/215147685-317c95a8-5ceb-4d1d-8177-29bbce62ad4e.png)

###### DB is deployed through AWS EBS configured with an RDS instance
![image](https://user-images.githubusercontent.com/31986528/215148566-6e5a2051-0cc1-4d9c-aee1-f66e860c4088.png)

# Final Thoughts
###### What did you think of the project?
I thought this project was a great chance to show what I know, as well as to define what needs more improvement on my end. For example, I think I have a clear understanding about how data is read between the client and server and how to display and manipulate it, but I do need more practice with UI tools.
I also wrote the SQL script to make the table for the testing enviornment and implemented some simple unit tests using JUnit. For CRUD, JPA was used to implement its repositories for easy access to its already defined operations. 
During training, I worked with pure JDBC Template, so getting to play around with JPA was really fun! Another skill I picked up in training was using Thymeleaf to access Java data via templates. The templates themselves were styled with HTML, CSS, and Bootstrap. 
One of my favorite parts was definitely getting to work with AWS. I ended up using the root account to create an IAM User specifically for this project with the necessary permissions. That IAM user was used to deploy AWS Elastic Beanstalk and to configure security group settings for the RDS so I could connect to it (and populate it) with MySQL.
###### What didn’t you like about the project?
There isn't anything I disliked. I do think the instructions could have been clearer, and in the workplace, I would definitely ask for specification on some of the requirements,
but overall, I did like that it was open-ended enough to the point where I could explore the possibilities on my own and to come up with what I have. I had wanted to experiment with AWS for a while so this was a great opportunity to just that. I selected Spring Boot MVC and Java because those are what I'm most familiar with at the moment. For future UI implementations, I would definitely love to try out React again!
###### Anything else you would like to share?
I actually had a lot of fun working on this! I know there is still a lot to improve on, but I am willing to put in the time and effort to ensure that I follow the best practices and to become great at what I do.
