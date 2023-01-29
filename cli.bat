
@REM TODO rework to BAT

doskey run-schedule= wt -w 0 new-tab cmd /k "title schedule & mvn spring-boot:run -f d:\prg\java\ecrm\schedule.rest\pom.xml -Dspring-boot.run.arguments=--config.url=localhost:8083 & exit" ; focus-tab -t 0
doskey stop-schedule= curl -X POST 127.0.0.1:8081/actuator/shutdown


doskey run-student= wt -w 0 new-tab cmd /k "title student & mvn spring-boot:run -f d:\prg\java\ecrm\student.rest\pom.xml -Dspring-boot.run.arguments=--config.url=localhost:8083 & exit" ; focus-tab -t 0
doskey stop-student= curl -X POST 127.0.0.1:8082/actuator/shutdown

doskey run-config= wt -w 0 new-tab cmd /k "title config & mvn spring-boot:run -f d:\prg\java\ecrm\config.server\pom.xml & exit" ; focus-tab -t 0
doskey stop-config= curl -X POST 127.0.0.1:8083/actuator/shutdown