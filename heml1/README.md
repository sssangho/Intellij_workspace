# 스프링부트 게시판 애플리케이션

스프링부트 3.2와 JDK 17을 사용한 게시판 애플리케이션입니다. H2 데이터베이스를 사용하며, Helm을 통해 Minikube에 배포됩니다.

## 기술 스택

- Spring Boot 3.2
- JDK 17
- H2 Database
- Thymeleaf
- Bootstrap 5
- Docker
- Kubernetes
- Helm

## 사전 요구사항

- JDK 17
- Docker
- Minikube
- Helm
- Gradle

## 설치 방법

1. Minikube 시작:
```bash
minikube start
```

2. 프로젝트 클론:
```bash
git clone [프로젝트 URL]
cd [프로젝트 디렉토리]
```

## 실행 방법

### 로컬에서 실행

1. 애플리케이션 빌드:
```bash
./gradlew bootRun
```

2. 브라우저에서 접속:
```
http://localhost:8080/board
```

### Minikube에 배포

1. 배포 스크립트 실행:
```bash
chmod +x deploy.sh
./deploy.sh
```

2. 서비스 URL 확인:
```bash
minikube service board-app --url
```

3. 브라우저에서 접속:
위 명령어로 출력된 URL에 `/board`를 추가하여 접속

## 초기 데이터

애플리케이션 시작 시 자동으로 다음 테스트 데이터가 로드됩니다:
- 환영 메시지
- 게시판 사용 방법
- 스프링부트 게시판 소개

## 정리 방법

1. 정리 스크립트 실행:
```bash
chmod +x cleanup.sh
./cleanup.sh
```

2. Minikube 중지 (선택사항):
```bash
minikube stop
```

## 주요 기능

- 게시글 CRUD (생성, 조회, 수정, 삭제)
- 부트스트랩 기반 반응형 UI
- H2 데이터베이스 사용
- RESTful API
- Thymeleaf 템플릿 엔진

## 디렉토리 구조

```
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/board/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── entity/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   └── resources/
│   │       └── templates/
│   └── test/
├── helm/
│   └── board-app/
├── build.gradle
├── Dockerfile
├── deploy.sh
└── cleanup.sh
```

## 문제 해결

1. Minikube 관련 문제:
```bash
minikube delete
minikube start
```

2. Docker 이미지 관련 문제:
```bash
docker system prune -a
```

3. Helm 차트 관련 문제:
```bash
helm uninstall board-app
helm install board-app ./helm/board-app
```

## 라이선스

이 프로젝트는 MIT 라이선스를 따릅니다. 