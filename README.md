# One - Planner

### Description
One - Planner는 웹 기술을 활용하여 개인의 일정 관리를 위한 맞춤형 플래너를 제공하는 프로젝트입니다. 
사용자는 효율적인 일정 관리를 통해 일상 생활의 질을 향상시킬 수 있으며, 다양한 웹 개발 기술의 적용을 통해 개인화된 경험을 제공받습니다.

### Features

- **사용자 인증 및 관리**: 로그인, 회원가입 기능과 이메일을 통한 사용자 인증을 제공합니다. 이를 통해 사용자는 안전하게 자신의 계정을 생성하고 관리할 수 있습니다.
- **소셜 미디어 연동**: 네이버, 카카오, 구글 등 다양한 외부 SNS 계정과의 연동을 지원합니다. 사용자는 이를 통해 쉽고 빠르게 로그인할 수 있으며, SNS 정보를 활용한 서비스 경험을 제공받을 수 있습니다.
- **일정 관리**: 사용자는 풀 캘린더 API를 통해 일정을 생성, 조회, 수정, 삭제할 수 있습니다. 실시간 일정 관리 및 Google Calendar와의 연동을 통해 복잡한 일정도 쉽게 관리할 수 있습니다.
- **이메일 인증**: 안전한 사용자 인증 및 서비스 이용을 위해 이메일 인증 기능을 제공합니다. 사용자는 이메일을 통해 계정을 안전하게 활성화하고, 서비스 접근 권한을 얻을 수 있습니다.
- **미니 플래너 및 사용자 인터페이스**: 사용자는 미니 플래너 기능을 통해 메인 캘린더의 요약된 정보를 빠르게 확인할 수 있으며, 직관적이고 사용자 친화적인 인터페이스를 통해 서비스를 이용할 수 있습니다.
- **백엔드 서버 및 데이터베이스 연동**: 안정적인 서비스 운영을 위해 백엔드 서버와 데이터베이스 간의 연동을 통해 데이터의 일관성과 신뢰성을 보장합니다. 사용자의 요청은 AJAX를 통해 비동기적으로 처리되며, 이는 사용자 경험을 향상시킵니다.

### System Requirements
- **Programming Language**: Java 17
- **Build Tool**: Gradle
  - `org.springframework.boot` version `3.2.2`
  - `io.spring.dependency-management` version `1.1.4`
- **Web Framework**: Spring Boot `3.2.2`
- **Database**: Oracle Database 19c 이상 with JDBC
- **Front-end**:
  - HTML5, CSS3, JavaScript
  - jQuery `3.7.1`, jQuery UI `1.13.1`
  - Bootstrap `5.3.2`, Popper.js `2.11.7`
  - FullCalendar `6.1.10`, flatpickr `4.6.13`, moment `2.30.1`, sweetalert2 `11.10.4`

- **Security**:
  - Spring Security, including OAuth2 Client support

- **Additional Libraries**:
  - Lombok, log4jdbc-log4j2 for JDBC logging
  - Spring Boot DevTools for development-time utilities
  - Jackson Dataformat JSR310, Jackson Databind for JSON processing
  - Spring Boot Starter Mail for email functionality

### Getting Started

프로젝트를 시작하기 위한 기본 단계는 다음과 같습니다. 프로젝트의 세부 설치 및 실행 방법은 [프로젝트 Wiki](https://example.com/one-planner/wiki)를 참조하세요.

1. **프로젝트 클론하기**

   Git을 사용하여 프로젝트를 로컬 시스템에 복제합니다.
   ```bash
   git clone https://example.com/one-planner.git
   ```

2. **프로젝트 디렉토리로 이동**
   
   클론된 프로젝트의 디렉토리로 이동합니다.
   ```bash
   cd one-planner
   ```

3. **환경 설정 파일 구성**

   `application.properties` 또는 `application.yml` 파일을 열고 필요한 환경 설정(예: 데이터베이스 연결 정보, 외부 API 키 등)을 구성합니다. 이 단계에서는 실제 운영 환경에 맞는 값을 입력해야 합니다.
   *주의: 민감한 정보는 환경 변수를 통해 관리하는 것이 안전합니다.*

4. **Spring Boot 애플리케이션 실행 (Linux/Mac)**

   Linux 또는 Mac 환경에서 다음 명령어를 사용하여 Spring Boot 애플리케이션을 실행합니다.
   ```bash
   ./gradlew bootRun
   ```
   이 명령어는 Gradle Wrapper를 사용하여 Spring Boot 애플리케이션을 실행합니다. 프로젝트가 성공적으로 시작되면, 웹 브라우저를 통해 애플리케이션에 접근할 수 있습니다.

5. **Spring Boot 애플리케이션 실행 (Windows)**

   Windows 환경에서는 CMD 또는 PowerShell을 사용하여 다음과 같이 애플리케이션을 실행할 수 있습니다.

   - **CMD에서 실행하기**
     ```cmd
     gradlew bootRun
     ```
   
   - **PowerShell에서 실행하기**
     ```powershell
     .\gradlew bootRun
     ```

### Source Architecture
프로젝트의 주요 소스 구조는 다음과 같습니다. 세부 구성 및 설명은 프로젝트 문서 내에서 확인할 수 있습니다.
```
com.example
├── config
│   ├── RestTemplateConfig.java
│	├── SecurityConfig.java
│   └── UserPasswordEncoder.java
├── controller
│   ├── HomeController.java
│	├── UserController.java
│	├── UserApiController.java
│   └── PlannerApiController.java
├── domain
│   ├── Alarm.java
│   ├── Plan.java
│   ├── SnsInfo.java
│   ├── User.java
│   └── UserLog.java
├── dto
│   ├── EventDto.java
│   └── LoginDto.java
├── emailverify
│   ├── EmailConfig.java
│   ├── EmailService.java
│   └── EmailVerificationController.java
├── oauth2
│   ├── exception
│   ├── handler
│   ├── service
│   ├── user
│   ├── util
│   └── HttpCookieOauth2AuthorizationRequestRepository.java
├── repository
│   ├── PlannerRepository.java
│   ├── SnsInfoRepository.java
│   ├── UserLogRepository.java
│   └── UserRepository.java
├── service
│   ├── PlannerService.java
│   └── UserService.java
├── util
│   └── BooleanToNumberConverter.java
└── PlannerApplication.java
```

### Wiki link (or Homepage link)
- 프로젝트 Wiki: [One - Planner Wiki](https://example.com/one-planner/wiki)
- 홈페이지 링크: [One - Planner Home](https://example.com/one-planner)

### License
이 프로젝트는 MIT 라이선스를 따릅니다. 라이선스에 대한 자세한 정보는 [LICENSE](LICENSE) 파일을 참조하세요.
