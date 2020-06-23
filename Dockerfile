FROM java:8
LABEL maintainer="corentin.barban@toulouse.miage.fr"
VOLUME /tmp
EXPOSE 8090
ADD target/miagesousleaucomposite-0.0.1-SNAPSHOT.jar miagesousleaucomposite-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","miagesousleaucomposite-0.0.1-SNAPSHOT.jar"]
