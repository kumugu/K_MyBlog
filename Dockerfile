FROM mysql:8.0

# 데이터베이스 덤프 파일을 컨테이너에 복사
COPY backup.sql /docker-entrypoint-initdb.d/

# MySQL 초기화 시 덤프 파일을 사용해 데이터베이스 복원
ENV MYSQL_ROOT_PASSWORD=my-secret-pw
ENV MYSQL_DATABASE=myblog_db

# 데이터베이스 복원 후 MySQL 실행
CMD ["mysqld"]
