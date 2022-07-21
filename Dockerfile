FROM eclipse-temurin:17-jdk-alpine as builder

RUN apk add tzdata
RUN cp /usr/share/zoneinfo/America/Bogota /localtime

RUN jlink --compress=2 --module-path /opt/java/openjdk/jmods/ \
--add-modules java.base,java.logging,java.xml\
,jdk.unsupported,java.sql,java.naming,java.desktop\
,java.management,java.security.jgss,java.instrument\
,jdk.management,jdk.crypto.cryptoki \
--no-header-files --no-man-pages --output /jlinked

FROM alpine:latest

COPY --from=builder /localtime /etc/localtime/
RUN echo "America/Bogota" > /etc/timezone \
    && addgroup -S user \
    && adduser -S user -G user
USER user

ENV JAVA_HOME /opt/jdk/
ENV PATH $JAVA_HOME/bin:$PATH

COPY --from=builder /jlinked /opt/jdk/

VOLUME /tmp
ADD ./build/libs/archetype-1.0.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=America/Bogota","-jar","/app.jar"]

