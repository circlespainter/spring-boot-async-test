#!/bin/sh
# java -javaagent:instrumentLibs/quasar-core-with-asm-0.6.1.jar -jar build/libs/spring-boot-async-test-0.1.0-SNAPSHOT.jar --debug
java -javaagent:instrumentLibs/quasar-core-with-asm-0.6.1.jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -jar build/libs/spring-boot-async-test-0.1.0-SNAPSHOT.jar --debug
