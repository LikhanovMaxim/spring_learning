## Spring Boot
Link:
http://localhost:8092/

### Links in this app
- Simple GET request: http://localhost:8092/ 
- Spring MVC:    
     - http://localhost:8092/hello?name=User
     - http://localhost:8092/hello
 - Cache
    - http://localhost:8092/cache?id=10

### Use a docker:
- Learn
    1) First learn it: https://docs.docker.com/get-started/part2/
    2) After learn this: https://spring.io/guides/gs/spring-boot-docker/ 
- Configure:
    1) look changes into build.gradle, application.yml
    2) look Dockerfile
        2) change path to your main class
- Build an image:
    1) In CMD: cd spring_learning/
    2) Run: gradlew build docker
- Run in Docker Toolbox
    1) Check that image was created: docker image ls
        1) You should see nameImage=${group from build.gradle}/${bootJar.baseName from build.gradle}
    2) Run: docker run -p 4000:8092 -t ${nameImage}
        2) First port(4000) - container’s published port
        2) Second port(8092) - application port. Must be the same as in application.yml
        2) Example: docker run -p 4000:8092 -t likhanov/spring-boot-learning
    3)  Go to http://localhost:4000/ OR for win7 http://192.168.99.100:4000/
    3) Share
        3) Save: docker save -o e:/new_file.tar likhanov/spring-boot-learning
        3) Load: docker load -i e:/new_file.tar
        
 ### Branches:
 - spring-boot http://localhost:8092/
 - spring-boot_and_freeMarker
     - http://localhost:8092/hello?name=User
     - http://localhost:8092/hello
     - https://hellokoding.com/spring-boot-hello-world-example-with-freemarker/
 - divide_to_modules templates is moved in another folder
 - refactoring-gradle : added lombok, TODO refactor gradle files
 - logging added lib
 - rest-controller : added handling of 404 error
 - docker