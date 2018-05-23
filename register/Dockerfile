FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD ./target/register.jar app.jar
COPY ./target/lib/*.jar /lib/
ENV JAVA_OPTS="\
 -server \
 -Djava.security.egd=file:/dev/./urandom\
 -Xms16m\
 -Xmx48m\
 -XX:MaxMetaspaceSize=64m\
 -XX:CompressedClassSpaceSize=8m\
 -Xss256k\
 -Xmn8m\
 -XX:InitialCodeCacheSize=4m\
 -XX:ReservedCodeCacheSize=8m\
 -XX:MaxDirectMemorySize=16m"
ENTRYPOINT java ${JAVA_OPTS} -jar /app.jar --spring.profiles.active=prod
HEALTHCHECK --interval=15s  --timeout=3s --retries=20 \
  CMD curl -fs http://0.0.0.0:8080/go || exit 1
