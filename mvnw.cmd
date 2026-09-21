@echo off
setlocal
set "MAVEN_HOME=%~dp0.tools\apache-maven-3.9.16"
set "PROJECT_JAVA_HOME=C:\Program Files\Java\jdk-21.0.12"
if exist "%PROJECT_JAVA_HOME%\bin\java.exe" set "JAVA_HOME=%PROJECT_JAVA_HOME%"
"%MAVEN_HOME%\bin\mvn.cmd" -Dmaven.repo.local="%~dp0.tools\m2" %*
