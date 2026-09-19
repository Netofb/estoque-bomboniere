@echo off
setlocal
set "MAVEN_HOME=%~dp0.tools\apache-maven-3.9.16"
"%MAVEN_HOME%\bin\mvn.cmd" -Dmaven.repo.local="%~dp0.tools\m2" %*
