# AWS EC2에서 Docker를 이용한 Spring Boot, React, MySQL 배포 가이드

## A. 로컬 환경에서 애플리케이션 빌드 및 Docker 이미지 생성

### 1. Spring Boot 프로젝트 빌드

```bash
cd /path/to/springboot-project
./gradlew build
```

### 2. React 프로젝트 빌드

```bash
cd /path/to/react-project
npm install
npm run build
```

### 3. Dockerfile 작성

#### Spring Boot Dockerfile
```dockerfile
# OpenJDK 17 기반 이미지 사용
FROM openjdk:17-jdk-slim

# 애플리케이션 JAR 파일을 /app 폴더로 복사
COPY build/libs/myblog-0.0.1-SNAPSHOT.jar /app/myblog.jar

# 애플리케이션 포트 8080을 공개
EXPOSE 8080

# 애플리케이션 실행 명령어
ENTRYPOINT ["java", "-jar", "/app/myblog.jar"]
```

#### React Dockerfile
```dockerfile
# Node.js 기반 이미지 사용
FROM node:16 AS build

# 작업 디렉토리 설정
WORKDIR /app

# 패키지 파일 복사 후 의존성 설치
COPY package*.json ./
RUN npm install

# 리액트 프로젝트 소스 코드 복사
COPY . .

# 빌드 생성
RUN npm run build

# nginx 이미지로 변경
FROM nginx:alpine

# 빌드된 파일을 nginx의 html 폴더로 복사
COPY --from=build /app/build /usr/share/nginx/html

# nginx 포트 80 노출
EXPOSE 80

# nginx 실행
CMD ["nginx", "-g", "daemon off;"]
```

#### MySQL Dockerfile
```dockerfile
FROM mysql:8.0
ENV MYSQL_DATABASE=myblog_db
ENV MYSQL_USER=rmarn
ENV MYSQL_PASSWORD=1234
ENV MYSQL_ROOT_PASSWORD=my-secret-pw
CMD ["mysqld"]
```

### 4. Docker 이미지 빌드 및 Docker Hub 업로드

```bash
docker login
```
#### Spring Boot
```bash
docker build -t myblog-springboot .
docker tag myblog-springboot kumugu/k_myblog:myblog-springboot
docker push kumugu/k_myblog:myblog-springboot
```

#### React
```bash
docker build -t myblog-react .
docker tag myblog-react kumugu/k_myblog:myblog-react
docker push kumugu/k_myblog:myblog-react
```

#### MySQL
```bash
docker build -t myblog-mysql .
docker tag myblog-mysql kumugu/k_myblog:myblog-mysql
docker push kumugu/k_myblog:myblog-mysql
```

-----

## B. EC2 환경 구성 및 배포

### 1. EC2 인스턴스 생성
- AWS 콘솔에서 EC2 인스턴스 생성
- Ubuntu OS 선택
- 보안 그룹 설정:
  - HTTP (80)
  - React (3000)
  - Spring Boot (8080)
  - MySQL (3306) - 내부에서만 접근 가능하도록 설정하는게 좋음

### 2. EC2 인스턴스 연결

```bash
# 키페어 주소, 공용ip 
ssh -i C:\workspace\AWS\KeyPair\K_MyBlog_Server.pem ubuntu@<EC2-PUBLIC-IP>
```

### 3. EC2 기본 설정

```bash
# 시스템 업데이트
sudo apt-get update -y
sudo apt-get upgrade -y
```

### 4. JDK 설치

```bash
sudo apt-get install openjdk-17-jdk -y
```

### 5. Docker 설치 및 설정

```bash
# Docker 설치
sudo apt-get update
sudo apt-get install docker.io -y

# Docker 서비스 시작 및 자동 시작 설정
sudo systemctl start docker
sudo systemctl enable docker

# Docker 권한 설정
sudo usermod -aG docker $USER
exit

# 재접속
ssh -i C:\workspace\AWS\KeyPair\K_MyBlog_Server.pem ubuntu@<EC2-PUBLIC-IP>
```

### 6. Docker 이미지 Pull 및 실행

```bash
# Docker Hub에서 이미지 가져오기
docker login
docker pull kumugu/k_myblog:myblog-react
docker pull kumugu/k_myblog:myblog-springboot
docker pull kumugu/mysql-db:myblog-mysql

# MySQL 컨테이너 먼저 실행 (다른 컨테이너들이 의존하므로)
docker run -d \
  --name myblog-mysql \
  -e MYSQL_DATABASE=myblog_db \
  -e MYSQL_USER=rmarn \
  -e MYSQL_PASSWORD=1234 \
  -e MYSQL_ROOT_PASSWORD=my-secret-pw \
  -p 3306:3306 \
  kumugu/mysql-db:myblog-mysql

# MySQL이 완전히 시작될 때까지 잠시 대기
sleep 30

# Spring Boot 애플리케이션 실행 (MySQL 컨테이너와 연결)
docker run -d \
  --name myblog-springboot \
  --link myblog-mysql:mysql \
  -p 8080:8080 \
  kumugu/k_myblog:myblog-springboot

# React 애플리케이션 실행
docker run -d \
  --name myblog-react \
  -p 3000:80 \
  kumugu/k_myblog:myblog-react
```

### 7. 애플리케이션 설정

#### Spring Boot (application.properties)
```properties
spring.application.name=myblog

# MySQL (로컬 Docker 컨테이너)
spring.datasource.url=jdbc:mysql://localhost:3306/myblog_db
spring.datasource.username=rmarn
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

-----

## C. 배포 확인

- Spring Boot API 확인: 
    curl http://localhost:8080
- React 웹사이트 확인:
    curl http://localhost:3000  
- MySQL 연결 확인:
    docker exec -it myblog-mysql mysql -u kumugu -p