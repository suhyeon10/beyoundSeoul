# Start with a base image containing Java runtime
FROM openjdk:17-alpine

# Add a volume to /tmp
VOLUME /home/springboot
# Make port 8080 available to the world outside this container
EXPOSE 8092
# The application's jar file
ARG JAR_FILE=./build/libs/youngsquad.jar

# Add the application's jar to the container
ADD ${JAR_FILE} /home/springboot/youngsquad.jar

# Setting environment variables
# Note: this application relies on environment variables that contain secrets. Can pass along to image via:
ENV JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8080
# Run the jar file
ENTRYPOINT ["java","-Duser.timezone='Asia/Seoul'","-jar","/home/springboot/youngsquad.jar"]

