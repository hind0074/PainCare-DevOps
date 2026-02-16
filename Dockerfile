FROM tomcat
WORKDIR /app
# RUN mkdir -p /usr/local/tomcat/webapps/PainCare/assets/avatars/
# RUN mkdir -p /usr/local/tomcat/webapps/PainCare/assets/blogs-images/

COPY /config/server.xml /usr/local/tomcat/conf/

COPY /build/PainCare.war /usr/local/tomcat/webapps/ROOT.war