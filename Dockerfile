FROM tomcat
WORKDIR /app
# RUN mkdir -p /usr/local/tomcat/webapps/PainCare/assets/avatars/
# RUN mkdir -p /usr/local/tomcat/webapps/PainCare/assets/blogs-images/

COPY /config/server.xml /usr/local/tomcat/conf/

COPY target/your-application-1.0.0.war /usr/local/tomcat/webapps/ROOT.war

COPY opentelemetry-javaagent.jar /otel.jar

# Configuration OpenTelemetry pour Jaeger (OTLP HTTP)
ENV OTEL_SERVICE_NAME=PainCare
ENV OTEL_EXPORTER_OTLP_ENDPOINT=http://jaeger:4318
ENV OTEL_EXPORTER_OTLP_PROTOCOL=http/protobuf

# Activer le Java Agent 
ENV CATALINA_OPTS="-javaagent:/otel.jar"




