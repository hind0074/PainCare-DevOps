FROM tomcat
WORKDIR /app
# RUN mkdir -p /usr/local/tomcat/webapps/PainCare/assets/avatars/
# RUN mkdir -p /usr/local/tomcat/webapps/PainCare/assets/blogs-images/

COPY /config/server.xml /usr/local/tomcat/conf/

COPY target/your-application-1.0.0.war /usr/local/tomcat/webapps/ROOT.war
