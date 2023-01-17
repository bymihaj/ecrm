
@REM TODO rework to BAT

doskey run-schedule= wt -w 0 new-tab cmd /k "title schedule & mvn spring-boot:run -f d:\prg\java\ecrm\schedule.rest\pom.xml & exit" ; focus-tab -t 0
doskey stop-schedule= curl -X POST 127.0.0.1:8081/actuator/shutdown


doskey run-student= wt -w 0 new-tab cmd /k "title student & mvn spring-boot:run -f d:\prg\java\ecrm\student.rest\pom.xml & exit" ; focus-tab -t 0
doskey stop-student= curl -X POST 127.0.0.1:8082/actuator/shutdown
