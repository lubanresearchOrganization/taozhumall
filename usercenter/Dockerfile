FROM registry.lubanresearch.com:5000/baseui:0.1
VOLUME /tmp
COPY ./target/lib/*.jar /lib/
ADD ./target/usercenter.jar app.jar
ENTRYPOINT java ${JAVA_OPTS} -jar /app.jar