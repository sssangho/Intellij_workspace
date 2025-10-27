# 사용자 관리 시스템

Spring Boot 3.1.5 기반의 RESTful API와 다양한 프론트엔드 구현을 포함한 사용자 관리 시스템입니다.

## 기술 스택

- Backend:
  - Spring Boot 3.1.5
  - JDK 17
  - Gradle
  - H2 Database
  - JPA
  - Swagger UI

- Frontend:
  - jQuery
  - Vue.js
  - React

## 실행 방법

1. 백엔드 서버 실행
```bash
./gradlew bootRun
```

2. 프론트엔드 개발 서버 실행
```bash
npm install
npm run serve
```

3. 접속 URL
- 백엔드 API: http://localhost:9110
- 프론트엔드 (Vue.js): http://localhost:9111
- Swagger UI: http://localhost:9110/swagger-ui.html
- H2 콘솔: http://localhost:9110/h2-console

## H2 데이터베이스 접속 정보

- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: (비어있음)

## 테스트 데이터

다음 SQL을 H2 콘솔에서 실행하여 테스트 데이터를 추가할 수 있습니다:

```sql
INSERT INTO users (NAME, EMAIL, AGE) VALUES 
('김철수', 'kim@example.com', 25),
('이영희', 'lee@example.com', 30),
('박민수', 'park@example.com', 28),
('정지은', 'jung@example.com', 35),
('최동욱', 'choi@example.com', 27);
```

## API 엔드포인트

- GET /api/users - 모든 사용자 조회
- GET /api/users/{id} - 특정 사용자 조회
- POST /api/users - 새로운 사용자 생성
- PUT /api/users/{id} - 사용자 정보 수정
- DELETE /api/users/{id} - 사용자 삭제

## 주요 기능

1. 사용자 관리
   - 사용자 목록 조회
   - 새로운 사용자 추가
   - 사용자 정보 수정
   - 사용자 삭제

2. 프론트엔드 구현
   - Vue.js: 반응형 데이터 처리와 컴포넌트 기반 구현
   - Vuetify를 사용한 모던한 UI/UX

## 주의사항

1. H2 데이터베이스는 메모리 모드로 실행되므로 애플리케이션 재시작 시 데이터가 초기화됩니다.
2. 백엔드 서버(9110 포트)가 실행 중이어야 프론트엔드가 정상적으로 동작합니다.
3. Swagger UI를 통해 API 문서를 확인하고 테스트할 수 있습니다. 