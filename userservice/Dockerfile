FROM registry.lubanresearch.com:5000/baseservice
VOLUME /tmp
ADD ./target/userservice.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]