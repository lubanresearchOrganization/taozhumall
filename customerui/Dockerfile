FROM registry.lubanresearch.com:5000/baseui
VOLUME /tmp
ADD ./target/customerui.jar app.jar
ENTRYPOINT java ${JAVA_OPTS} -jar /app.jar