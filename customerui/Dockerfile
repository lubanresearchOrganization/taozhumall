FROM registry.lubanresearch.com:5000/baseui:0.1
VOLUME /tmp
ADD ./target/customerui.jar app.jar
ENTRYPOINT java ${JAVA_OPTS} -jar /app.jar --spring.profiles.active=prod