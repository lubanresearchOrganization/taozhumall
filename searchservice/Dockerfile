FROM registry.lubanresearch.com:5000/baseservice:0.1
VOLUME /tmp
ADD ./target/searchservice.jar app.jar
ENTRYPOINT java ${JAVA_OPTS} -jar /app.jar