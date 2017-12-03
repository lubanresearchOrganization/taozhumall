FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD ./target/orderservice.jar app.jar
COPY ./target/lib/*.jar /lib/
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]