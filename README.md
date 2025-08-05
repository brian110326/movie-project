# 🎬 Movie Booking MSA

개인 프로젝트로 개발한 **영화 예매 시스템**입니다.  
MSA 기반으로 설계하고, 실무에서 사용하는 다양한 백엔드 기술을 직접 적용했습니다.

---

## 🔧 기술 스택

- Java 17, Spring Boot 3.5, Maven
- MariaDB, Spring Data JPA
- JWT 인증, Spring Security
- Redis 캐시
- Kafka (비동기 이벤트 처리)
- Feign Client (서비스 간 통신)
- Swagger (API 문서화)
- Eureka, Spring Cloud Gateway
- **Docker / Docker Hub**

---

## 🧱 서비스 구성

| 서비스명           | 설명                               |
|--------------------|------------------------------------|
| user-service       | 회원가입, 로그인 (JWT 인증)         |
| movie-service      | 영화 등록 및 조회                  |
| theater-service    | 상영관 정보 관리                   |
| seat-service       | 좌석 정보 관리                     |
| schedule-service   | 영화 상영 일정 관리                |
| configure-service  | 시간표 구성 및 예매 준비           |
| booking-service    | 영화 예매 및 좌석 예약 처리        |
| payment-service    | 결제 처리                          |
| gateway-service    | API Gateway (라우팅)               |
| discovery-service  | 서비스 등록/탐색 (Eureka)          |

---

## ✅ 주요 기능

- JWT 기반 로그인 및 인증
- Feign Client + Kafka로 서비스 간 통신
- Redis를 통한 캐싱
- Swagger로 API 문서 자동화
- **모든 서비스 Docker 이미지화 및 배포**

---

## 🐳 Docker 이미지

각 마이크로서비스는 개별 Docker 이미지로 빌드되어 Docker Hub에 업로드되어 있습니다.

> 🔗 [Docker Hub - 전체 이미지 보기](https://hub.docker.com/repositories/brian1103?search=movie)

- 컨테이너 실행 시, 모든 서비스는 동일한 사용자 정의 Docker 네트워크에 연결되어  
  `redis`, `kafka`, 각 micro service 등과 상호 통신 가능하도록 구성했습니다.

---

## ⚠️ 프로젝트 한계점

- 실제 개발한 모든 서비스는 Docker로 분리 배포했으며, Redis, Kafka 등 인프라도 개별 컨테이너로 구동했습니다.
- 하지만 로컬 환경의 자원 한계(CPU/RAM)로 인해 **모든 서비스를 동시에 실행하여 통합 테스트를 수행하는 데는 어려움이 있었습니다.**
- 대신 각 서비스별로 기능 테스트를 완료하였고, Docker 네트워크를 활용해 서비스 간 통신 구성이 가능한 구조는 마련되어 있습니다.

---

## 📌 참고

- 모든 기능은 혼자 구현한 **백엔드 중심의 MSA 프로젝트**입니다.
- Swagger 문서는 각 서비스 실행 시 `/swagger-ui/index.html` 경로에서 자동 제공됩니다.
