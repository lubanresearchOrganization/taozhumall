FROM registry.lubanresearch.com:5000/baseui:0.1
VOLUME /tmp
ADD ./target/shopui.jar app.jar
ENTRYPOINT java ${JAVA_OPTS} -jar /app.jar