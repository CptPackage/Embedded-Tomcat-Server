# Embedded Tomcat Server without any building tools (Maven,Ant,..)

IMPORTANT<br>
- Once you generate the JAR file you have to make sure that there is the "web-content" directory in the same path with it otherwise it won't work as it won't be able to serve anything

- The "tmp" directory is used by the server to compile the JSPs to servlet ".java" files then to ".class" files for the first time they are requested.
