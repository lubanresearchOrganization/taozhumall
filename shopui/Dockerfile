FROM registry.lubanresearch.com:5000/baseui:0.1
VOLUME /tmp
ADD ./target/shopui.jar app.jar
ENTRYPOINT java ${JAVA_OPTS} -jar /app.jar --spring.profiles.active=prod
HEALTHCHECK --interval=15s  --timeout=3s --retries=20 \
  CMD curl -fs http://localhost:8080/go || exit 1