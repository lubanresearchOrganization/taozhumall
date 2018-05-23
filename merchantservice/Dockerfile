FROM registry.lubanresearch.com:5000/baseservice:0.1
VOLUME /tmp
ADD ./target/merchantservice.jar app.jar
ENTRYPOINT java ${JAVA_OPTS} -jar /app.jar --spring.profiles.active=prod
HEALTHCHECK CMD curl --fail http://127.0.0.1:5004/go || exit 1