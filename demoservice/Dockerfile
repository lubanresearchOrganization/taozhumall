FROM registry.lubanresearch.com:5000/baseservice:0.1
VOLUME /tmp
ADD ./target/demoservice.jar app.jar
ENTRYPOINT java ${JAVA_OPTS} -jar /app.jar