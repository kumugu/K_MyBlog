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

# 빌드된 파일을 nginx을 html 폴더로 복사
COPY --from=build /app/build /usr/share/nginx/html

# nginx 포트를 80 노출
EXPOSE 80

# nginx 실행
CMD ["nginx", "-g", "daemon off;"]