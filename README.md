<form action="${pageContext.request.contextPath}/login">

# Verkhovna Rada of Ukraine deputy statistics API

# Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [For developers](#developer-start)
* [Authors](#authors)

# <a name="purpose"></a>Project purpose
API providing detailed statistics on Political Groups and People's Deputies of Verkhovna Rada of Ukraine, featuring next criteria: 
   * activity period 
   * political groups participated in
   * parliament convocation number
   * education
   * nationality
   * awards possessed      
   <hr>
   
# <a name="structure"></a>Project structure
   * Java 12
   * Maven 4.0.0
   * Spring boot 2.2
   * Spring data JPA
   * Swagger2 2.9.2
   * Lombok
   * JUnit 4
   * h2 database
   * opencsv 5.0
   <hr>
   
# <a name="developer-start"></a>For developers
1. Open the project in your IDE.    
2. Add it as maven project.
3. If you are going to run the project as docker image from local port, fill <docker.image.prefix> tag with your docker id.
4. Run the project. 
5. Authenticate via next credentials,  **login:** ***admin***, **password:** ***12345***.
6. Available url commands with instructions will be viewed on index page. 

Project is also available as [docker hub image](https://hub.docker.com/r/12482946421/jv-deputies):  
open cmd/bash\
docker login (input your docker id and password if you aren't logged in)\
docker pull 12482946421/jv-deputies\
docker run 12482946421/jv-deputies:latest\
check localhost:8080\
stop the application with ctrl+c in cmd\
don't forget to stop the docker container:\
docker ps\
docker stop container_name
<hr>

# <a name="authors"></a>Authors
   * [Iurii](https://github.com/kenu21), [linkedin](https://www.linkedin.com/in/iurii-keniu-39188759/)   
   * [Roman](https://github.com/RomanMinevich), [linkedin](https://www.linkedin.com/in/roman-minevich-401a2b152/)
   * [Heorhii](https://github.com/gkhrshch), [linkedin](https://www.linkedin.com/in/heorhii-khrushchov-ba6b01197/)
