@echo off
title iHybrid
"C:\Program Files (x86)\Java\jdk1.6.0_31\bin\java.exe" -Xmx800m -cp bin;deps/poi.jar;deps/mysql.jar;deps/mina.jar;deps/slf4j.jar;deps/slf4j-nop.jar;deps/jython.jar;log4j-1.2.15.jar; server.Server
pause