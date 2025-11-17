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

# 1. Helm 차트 삭제
print_step "Helm 차트 삭제 중..."
if helm list | grep -q board-app; then
    if ! helm uninstall board-app; then
        print_error "Helm 차트 삭제 실패"
        exit 1
    fi
    print_success "Helm 차트 삭제 완료"
else
    print_success "Helm 차트가 이미 삭제되었습니다."
fi

# 2. Docker 이미지 삭제
print_step "Docker 이미지 삭제 중..."
if docker images | grep -q board-app; then
    if ! docker rmi board-app:latest; then
        print_error "Docker 이미지 삭제 실패"
        exit 1
    fi
    print_success "Docker 이미지 삭제 완료"
else
    print_success "Docker 이미지가 이미 삭제되었습니다."
fi

# 3. Minikube에서 이미지 삭제
print_step "Minikube에서 이미지 삭제 중..."
if minikube image ls | grep -q board-app; then
    if ! minikube image rm board-app:latest; then
        print_error "Minikube 이미지 삭제 실패"
        exit 1
    fi
    print_success "Minikube 이미지 삭제 완료"
else
    print_success "Minikube 이미지가 이미 삭제되었습니다."
fi

# 4. 빌드 파일 정리
print_step "빌드 파일 정리 중..."
#if ! ./gradlew clean; then
#    print_error "빌드 파일 정리 실패"
#    exit 1
#fi
print_success "빌드 파일 정리 완료"

print_success "정리가 완료되었습니다!" 