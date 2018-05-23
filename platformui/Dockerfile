FROM registry.lubanresearch.com:5000/baseui:0.1
VOLUME /tmp
ADD ./target/platformui.jar app.jar
ENTRYPOINT java ${JAVA_OPTS} -jar /app.jar --spring.profiles.active=prod
HEALTHCHECK CMD curl --fail http://127.0.0.1:4003/go || exit 1