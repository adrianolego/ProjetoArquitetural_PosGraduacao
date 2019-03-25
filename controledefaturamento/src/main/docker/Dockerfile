FROM openjdk:8-jdk-alpine

VOLUME /tmp
RUN apk add --no-cache curl
RUN apk add --no-cache netcat-openbsd
RUN apk add --no-cache bash

ARG JAR_FILE
ARG UTIL_DIR
ENV JAVA_OPTS "-Xmx256m -Xms128m"

RUN apk add -U tzdata
RUN cp -f /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime

ADD ${JAR_FILE} app.jar

ADD ${UTIL_DIR}wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

ADD ${UTIL_DIR}entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
