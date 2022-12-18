FROM openjdk
ADD build/libs/ClevertechTestTask-0.0.1-SNAPSHOT.jar ClevertechTestTask-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/ClevertechTestTask-0.0.1-SNAPSHOT.jar"]
