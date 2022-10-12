FROM openjdk:17
ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "-Djwt.secret=${JWT_SECRET}","-Djwt.expired.access=${JWT_EXPIRED_ACCESS}","-Djwt.expired.refresh=${JWT_EXPIRED_REFRESH}", "-Dstorage.bucket=${STORAGE_BUCKET}","-Dstorage.endpoint=${STORAGE_ENDPOINT}","-Dstorage.access_key=${STORAGE_ACCESS_KEY}","-Dstorage.secret-key=${STORAGE_SECRET_KEY}","/app.jar"]