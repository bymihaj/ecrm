call mvn clean install -f ecrm.model/pom.xml
call mvn spring-boot:build-image -f schedule.rest/pom.xml
call mvn spring-boot:build-image -f student.rest/pom.xml
call mvn spring-boot:build-image -f ecrm.ui/pom.xml