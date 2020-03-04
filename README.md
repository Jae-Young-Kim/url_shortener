# URL Shorten web service sample application.
1. Requirements
    - JDK 8+
    - Gradle 5+

2. Dependencies
    - Spring Boot 2
        - Thymeleaf
        - Data JPA
        - Test
    - HSQLDB
    - JUnit5
    - Mockito3

3. Static code analysis tool
    - Checkstyle
        - for checking if Java source code complies with coding rules.
        - Rule: config/checkstyle/*.xml
        - Report: build/reports/checkstyle (after perform check)
    - SpotBugs
        - for find bugs in codes
        - Report: build/reports/spotbugs (after perform check)

4. Build commands
    - build(perform with below): `./gradlew clean build`
    - only test: `./gradlew test`
    - only perform code analysis
        - checkstyle: `./gradlew checkstyleMain checkstyleTest`
        - spotbugs: `./gradlew spotbugsMain spotbugsTest`

5. Running Locally
    - build dir: build/libs
    - `java -jar url_shortener-1.0-SNAPSHOT.jar`
    - Open your browser and connect to [http://localhost:8080](http://localhost:8080)
