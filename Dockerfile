FROM openjdk:17
ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "-Djwt.secret=${JWT_SECRET}","-Djwt.expired.access=${JWT_EXPIRED_ACCESS}","-Djwt.expired.refresh=${JWT_EXPIRED_REFRESH}","-Dspring.profiles.active=dev", "-Dstorage.bucketName=${STORAGE_BUCKET}","-Dstorage.endpoint=${STORAGE_ENDPOINT}","-Dstorage.accessKey=${STORAGE_ACCESSKEY}","-Dstorage.secretKey=${STORAGE_SECRETKEY}","/app.jar"]