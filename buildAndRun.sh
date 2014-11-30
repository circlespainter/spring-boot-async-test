#!/bin/sh
# /gradlew build && java -javaagent:instrumentLibs/quasar-core-with-asm-0.6.1.jar -jar build/libs/spring-boot-async-test-0.1.0-SNAPSHOT.jar
./gradlew build && java -javaagent:instrumentLibs/quasar-core-with-asm-0.6.1.jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005 -jar build/libs/spring-boot-async-test-0.1.0-SNAPSHOT.jar