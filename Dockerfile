FROM tomcat
COPY target/java-maven-calculator-web-app.war /usr/local/tomcat/webapps/java-maven-calculator-web-app.war
EXPOSE 8080 
CMD ["catalina.sh", "run"]
