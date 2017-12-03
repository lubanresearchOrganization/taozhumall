FROM jfisbein/alpine-oracle-jdk9
VOLUME /tmp
ADD ./target/register.jar app.jar
COPY ./target/lib/*.jar /lib/
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
