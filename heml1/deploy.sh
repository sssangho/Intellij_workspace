#!/bin/bash

# 색상 정의
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 함수 정의
print_step() {
    echo -e "${YELLOW}==> $1${NC}"
}

print_success() {
    echo -e "${GREEN}✓ $1${NC}"
}

print_error() {
    echo -e "${RED}✗ $1${NC}"
}

# 1. Minikube 상태 확인
print_step "Minikube 상태 확인 중..."
if ! minikube status &> /dev/null; then
    print_error "Minikube가 실행되지 않았습니다. Minikube를 시작합니다..."
    minikube start
else
    print_success "Minikube가 이미 실행 중입니다."
fi

# 2. jar 파일 생성
print_step "jar 파일 생성 중..."
#if ! ./gradlew bootJar; then
#    print_error "jar 파일 생성 실패"
#    exit 1
#fi
print_success "jar 파일 생성 완료"

# 3. Docker 이미지 빌드
print_step "Docker 이미지 빌드 중..."
if ! docker build -t board-app:latest .; then
    print_error "Docker 이미지 빌드 실패"
    exit 1
fi
print_success "Docker 이미지 빌드 완료"

# 4. Minikube에 이미지 로드
print_step "Minikube에 이미지 로드 중..."
if ! minikube image load board-app:latest; then
    print_error "이미지 로드 실패"
    exit 1
fi
print_success "이미지 로드 완료"

# 5. Helm 차트 배포
print_step "Helm 차트 배포 중..."
if ! helm install board-app ./helm/board-app; then
    print_error "Helm 차트 배포 실패"
    exit 1
fi
print_success "Helm 차트 배포 완료"

# 6. 서비스 상태 확인
print_step "서비스 상태 확인 중..."
kubectl get pods
kubectl get svc

# 7. 접속 URL 출력
print_step "접속 URL:"
minikube service board-app --url

print_success "배포가 완료되었습니다!" 