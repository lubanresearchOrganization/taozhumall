FROM registry.lubanresearch.com:5000/baseui
VOLUME /tmp
ADD ./target/platformui.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]