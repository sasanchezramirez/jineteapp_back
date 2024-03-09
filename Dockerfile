FROM openjdk:17
COPY ./ /usr/src/jineteapp-back
WORKDIR /usr/src/jineteapp-back
RUN sed -i 's/\r$//' gradlew && chmod +x ./gradlew
RUN ./gradlew build

# Utiliza un comodín para copiar cualquier archivo JAR generado en el directorio build/libs.
COPY build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
