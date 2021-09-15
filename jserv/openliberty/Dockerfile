FROM openjdk:11
LABEL maintainer="okular2020@gmail.com"

# change version to current builds
ENV RELEASE 2021-08-03_1900
ENV VERSION 21.0.0.9-cl210920210803-1900

RUN wget https://public.dhe.ibm.com/ibmdl/export/pub/software/openliberty/runtime/nightly/${RELEASE}/openliberty-all-${VERSION}.zip \
    && unzip openliberty-all-${VERSION}.zip -d /opt \
    && rm openliberty-all-${VERSION}.zip

ENV DEPLOYMENT_DIR /opt/wlp/usr/servers/jserv/dropins/
ENV CONFIG_DIR /opt/wlp/usr/servers/jserv/

COPY server.xml $CONFIG_DIR
# COPY postgresql-9.4-1201.jar /opt/wlp/lib/
COPY postgresql-42.2.23.jar /opt/wlp/lib/

CMD ["/opt/wlp/bin/server", "run", "jserv"]

