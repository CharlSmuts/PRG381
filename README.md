# PRG381

Apache tomcat 11.0.9 used. <br>
Ensure project folder is in apache tomcat /webapps folder <br>
Download jakarta.servlet-api-6.0.0.jar <br>
Copy to /lib folder and rename to jakarta.servlet.jar <br>

**Compile command example:** <br>
javac -cp "C:\apache-tomcat-11.0.9\lib\jakarta.servlet-api-6.1.0.jar" LogoutServlet.java <br>
copy .class file to WEB-INF/classes <br>

**Startup:**<br>
cd C:\apache-tomcat-11.0.9\bin <br>
startup.bat <br>

**In browser:** <br>
http://localhost:8080/PRG381Proj/dashboard.jsp <br>
