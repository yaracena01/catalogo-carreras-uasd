@echo off
cd /d "%~dp0"
if not exist build\classes mkdir build\classes
javac -encoding UTF-8 -sourcepath src -d build\classes -cp lib\mssql-jdbc-12.8.1.jre11.jar src\uasd\catalogo\Main.java
