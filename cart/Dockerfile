FROM registry.lubanresearch.com:5000/baseservice:0.1
VOLUME /tmp
ADD ./target/cart.jar app.jar

HEALTHCHECK CMD curl --silent --fail http://127.0.0.1:8080/go || exit 1

ENTRYPOINT java ${JAVA_OPTS} -jar /app.jar --spring.profiles.active=prod