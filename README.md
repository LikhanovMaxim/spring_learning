Spring Boot
Link:
http://localhost:8092/

branches:
- spring-boot http://localhost:8092/
- spring-boot_and_freeMarker
    http://localhost:8092/hello?name=User
    http://localhost:8092/hello
https://hellokoding.com/spring-boot-hello-world-example-with-freemarker/
- divide_to_modules templates is moved in another folder
- refactoring-gradle : added lombok, TODO refactor gradle files
- logging added lib
- rest-controller : added handling of 404 error

Use docker:
- First learn it: https://docs.docker.com/get-started/part2/
- After learn this: https://spring.io/guides/gs/spring-boot-docker/ and configure:
-- 1) look changes into build.gradle
-- 2) look Dockerfile
-- 2.1) change path to your main class
- Build:
-- 1) In CMD: cd spring_learning/
-- 2) Run: gradlew build docker
- Run in Docker Toolbox
-- 1) check that image was created: docker image ls
---        You should see nameImage=${group from build.gradle}/${bootJar.baseName from build.gradle}
-- 2) Run: docker run -p 4000:8092 -t ${nameImage}
---     First port(4000) - container’s published port
---     Second port(8092) - application port. Must be the same as in application.yml
---     Example: docker run -p 4000:8092 -t likhanov/spring-boot-learning
---
---     Go to http://localhost:4000/ OR for win7 http://192.168.99.100:4000/
-- 3) Share
-- 3.1) Save: docker save -o e:/new_file.tar likhanov/spring-boot-learning
-- 3.2) Load: docker load -i e:/new_file.tar