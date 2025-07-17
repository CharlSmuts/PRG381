# PRG381
Install Smart Tomcat plugin for IntelliJ (4.7.5 tested)

Edit Run/Debug Configurations and add Smart Tomcat as a new configuration. Select the location of your Apache Tomacat (tested on 11.0.9), choose src\Web as the deployment directory and a context path

Add the jakarta.servlet.jar and postgresql-42.7.7.jar files to the project Libraries under:
Settings > Project Structure > Project Settings > Libraries

Additionally copy the jakarta.servlet.jar and postgresql-42.7.7.jar files to the lib folder in the project
