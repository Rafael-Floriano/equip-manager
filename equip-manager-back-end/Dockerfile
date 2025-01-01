FROM maven:3.8.4-amazoncorretto-17 as builder

WORKDIR /app

COPY pom.xml ./

RUN mvn clean install -DskipTests

RUN ls -l /app/target

FROM amazoncorretto:17-alpine

WORKDIR /app

COPY --from=builder /app/target/equip-manager-back-end-0.0.1-SNAPSHOT.jar /app/equip-manager-back-end.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/equip-manager-back-end.jar"]
