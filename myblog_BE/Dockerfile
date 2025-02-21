# OpenJDK 17 사용
FROM openjdk:17-jdk-slim

# JAR 파일을 /app 폴더로 복사
COPY build/libs/myblog-0.0.1-SNAPSHOT.jar /app/myblog.jar

# 애플리케이션 포트 8080을 공개
EXPOSE 8080

# 애플리케이션 실행 명령어
ENTRYPOINT ["java", "-jar", "/app/myblog.jar"]