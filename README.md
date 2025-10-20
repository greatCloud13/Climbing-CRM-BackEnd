# Climbing-CRM

클라이밍 회원관리 시스템

<img width="2560" height="1440" alt="image" src="https://github.com/user-attachments/assets/f1d23b7b-ccbb-4e31-b624-96930556e5da" />

## 프로젝트 소개

클라이밍 센터의 회원권과 회원들을 관리하는 시스템입니다.

- 평소에 다니는 클라이밍 센터의 회원관리를 엑셀로 이용하고 있는 것을 본 후 이를 더욱 편하게 이용할 수 있도록 개선할 수 있지 않을까 하는 생각에서 시작하게되었습니다
- 클라이밍 센터를 1개 운영 혹은 2개 이상 운영중인 클라이밍 센터를 타겟 사용자로 기획
- 사용자들이 통계나 특정 업무를 엑셀에 비해 편하게 이용할 수 있도록 하는 것이 목표

## 기술 스택

### Backend
- Java 21
- Spring Boot 4.0.0
- Spring Data JPA
- Spring Security
- MySQL 8.0

### Build Tool
- Gradle

### Infra
- AWS EC2
- Docker

### 기타
- JWT
- Swagger/OpenAPI

## ⚙️ 주요 기능
- 대시보드를 통해 센터 회원관리에 대한 요약된 정보들을 파악 할 수 있는 기능
- 회원 관리를 통해 각 회원들에 대한 회원권 관리 기능
- 회원권에 대한 추가및 삭제 기능
- 회원들의 출석 정보 제공 기능
- 간단한 통계 기능으로 추이 확인 기능


### 1. 대시 보드
- 주요 현황 기능들을 요약해서 한눈에 볼 수 있는 기능

### 2. 회원 관리
- 각 회원들의 출석정보 및 등록 현황을 파악할 수 있는 기능

### 3. 회원권 관리
- 기간권 횟수권 수강 정보와 같은 회원권에 대한 추가 기능

## 🏗️ 아키텍처

```
![ClimbingCRM](https://github.com/user-attachments/assets/995bd7e0-8041-438e-85f8-aa275dae45f9)
```

### ERD
```
[ERD 이미지 또는 다이어그램]
```

## 🚀 실행 방법

### 사전 요구사항
- Java 21 이상
- MySQL 8.0 이상
- Gradle 9.1 이상

### 환경 설정
```bash
# application.yml 설정
cp src/main/resources/application.yml
# 제공받은 dev profile을 이용
```

### 실행
```bash
# 프로젝트 클론
git clone https://github.com/username/Climbing-CRM-BackEnd.git

# 프로젝트 디렉토리 이동
cd Climbing-CRM-BackEnd

# 빌드
./gradlew build

# 실행
./gradlew bootRun
```

### API 문서
```
http://localhost:8080/swagger-ui.html
```

## 📂 프로젝트 구조

```
src
├── main
│   ├── java
│   │   └── com.climbingCRM.climbingcrm
│   │       ├── entity
│   │       │
│   │       ├── DTO
│   │       │
│   │       ├── Controller
│   │       │
│   │       ├── service
│   │       │
│   │       ├── global
│   │       │   ├── config
│   │       │   ├── exception
│   │       │   └── util
│   │       └── ProjectApplication.java
│   └── resources
│       ├── application.yml
│       └── application-prod.yml
└── test
```

## 📅 개발 기간

- 전체 개발 기간: 2025.10.12 ~ 2025.10.19
- 기획 및 설계: 1주
- 기능 구현: N주
- 테스트 및 배포: N주

## 👤 개발자

- **이름** - [GitHub](https://github.com/greatcloud13) | [Email](greatcloud13@gmail.com)
