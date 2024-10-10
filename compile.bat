@ECHO OFF

javac -d out\ src\br\edu\ufpel\cache_simulator\*\*.java src\br\edu\ufpel\cache_simulator\*.java
cd out
jar cvfm ..\cache_simulator.jar Simulator.mf br\edu\ufpel\cache_simulator\*\*.class br\edu\ufpel\cache_simulator\*.class
cd..
jpackage --input . --main-jar cache_simulator.jar
del Simulator.jar
del out\br\*