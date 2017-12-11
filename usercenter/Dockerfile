FROM registry.lubanresearch.com:5000/baseui
VOLUME /tmp
ADD ./target/usercenter.jar app.jar
ENTRYPOINT java ${JAVA_OPTS} -jar /app.jar