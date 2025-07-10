# PRG381

Apache tomcat 11.0.9 used. 
Ensure project folder is in apache tomcat /webapps folder
Download jakarta.servlet-api-6.0.0.jar
Copy to /lib folder and rename to jakarta.servlet.jar

Compile command example:
javac -cp "C:\apache-tomcat-11.0.9\lib\jakarta.servlet-api-6.1.0.jar" LogoutServlet.java
copy .class file to WEB-INF/classes

startup:
cd C:\apache-tomcat-11.0.9\bin
startup.bat

In browser:
http://localhost:8080/PRG381Proj/dashboard.jsp
