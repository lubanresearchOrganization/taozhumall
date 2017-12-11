FROM registry.lubanresearch.com:5000/baseservice:0.1
VOLUME /tmp
ADD ./target/customerservice.jar app.jar
ENTRYPOINT ["java","
 -Djava.security.egd=file:/dev/./urandom\
 -Xms16m\
 -Xmx48m\
 -XX:MaxMetaspaceSize=64m\
 -XX:CompressedClassSpaceSize=8m\
 -Xss256k\
 -Xmn8m\
 -XX:InitialCodeCacheSize=4m\
 -XX:ReservedCodeCacheSize=8m\
 -XX:MaxDirectMemorySize=16m\
","-jar","/app.jar"]