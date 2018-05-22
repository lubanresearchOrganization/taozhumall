FROM registry.lubanresearch.com:5000/baseservice:0.1
VOLUME /tmp
ADD ./target/catagoryservice.jar app.jar
ENTRYPOINT java ${JAVA_OPTS} -jar /app.jar --spring.profiles.active=prod
HEALTHCHECK --interval=5s --timeout=3s \
  CMD curl -fs http://localhost:8080/go || exit 1