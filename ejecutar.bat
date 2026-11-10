@echo off
cd /d "%~dp0"
call "%~dp0compilar.bat"
if errorlevel 1 exit /b 1
java -cp "build\classes;lib\mssql-jdbc-12.8.1.jre11.jar" uasd.catalogo.Main
