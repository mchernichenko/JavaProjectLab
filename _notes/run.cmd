@echo off
rem set cp="./*;./lib/*;../../*;../../lib/*"
rem java -cp %cp% org.apache.zookeeper.inspector.ZooInspector

rem Пример запуска с настройками для удалённого мониторинга
rem в jconsole в Remote Process прописать localhost:10999

rem com.sun.management.jmxremote - включен/выключен мониторинг (true/false).
rem com.sun.management.jmxremote.port - порт, на который приходят входящие запросы от клиента.
rem com.sun.management.jmxremote.ssl - включен/выключен ssl (true/false).
rem com.sun.management.jmxremote.authenticate - требуется ли аутентификация (true/false).

set jmx1="-Dcom.sun.management.jmxremote.port=10999"
set jmx2="-Dcom.sun.management.jmxremote.authenticate=false"
set jmx3="-Dcom.sun.management.jmxremote.ssl=false"

java %jmx1% %jmx2% %jmx3% -jar JMX-test-1.0-SNAPSHOT.jar