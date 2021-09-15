FROM ocular2020/openliberty-openjdk11

COPY openliberty/server.env $CONFIG_DIR
COPY openliberty/jvm.options $CONFIG_DIR
COPY openliberty/bootstrap.properties $CONFIG_DIR
COPY openliberty/server.xml $CONFIG_DIR

ENV EXTERNALIZED_APPLICATION_CONFIG_DIR /config/jserv/

COPY src/main/resources/credentials.properties $EXTERNALIZED_APPLICATION_CONFIG_DIR
COPY src/main/resources/application.properties $EXTERNALIZED_APPLICATION_CONFIG_DIR

COPY target/jserv.war $DEPLOYMENT_DIR

