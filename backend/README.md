## Backend

![](https://img.shields.io/badge/IntelliJ-2019.3.3-orange)

![](https://img.shields.io/badge/SpringBoot-gradle-9cf)

### 기술스택

- IntelliJ로 Spring boot Gradle 형식으로 개발
- JPA를 사용한 데이터베이스와의 연동
- RESTful 개발
- Swagger을 활용한 REST API 문서화 제공
- AWS의 ec2의 서버의 MariaDB 사용

### 배포 방법

1. IntelliJ를 각각의 운영체제에 맞는 버전을 다운 받는다. 
2. /web-9to6/build/libs 아래의 XX.jar 파일을 ec2 server로 전송(winSCP를 사용하거나 scp -i "T02A409.pem 파일 경로" -r ./dist ubuntu@15.164.244.244:/home/ubuntu/web-9to6/back/)
3. ec2 server에서 cd web-9to6/back/로 이동 후 nohup java -jar XX.jar & 커맨드 실행



