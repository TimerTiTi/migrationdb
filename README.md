# TiTi MigrationDB
티티 데이터베이스 마이그레이션 배치 모듈 </br>
[티티 테스트 서버](https://github.com/TimerTiTi/TiTi_Backend)의 데이터베이스를 마이그레이션한다.

## Jobs
| JobName       | Description             |
|---------------|-------------------------|
| migrationJob  | 티티 데이터베이스 마이그레이션 |

## Getting Started

### 개발 환경

- [JDK 21](https://jdk.java.net/21/)
- [Spring Boot 3.1.7](https://spring.io/blog/2023/12/21/spring-boot-3-1-7-available-now/)
- [Spring Batch 5.0.4](https://spring.io/blog/2023/11/23/spring-batch-5-1-ga-5-0-4-and-4-3-10-available-now/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa/)([Hibernate 6.2.17.Final](https://hibernate.org/orm/releases/6.2/))


## 프로젝트 구성

```java
└──🔹migration-db
      └──📂src/main/java/com/titi/migrationdb
      │     ├── 📂batch
      │     │     ├── 📂config
      │     │     ├── 📂job
      │     │     ├── 📂service
      │     │     ├── 📂step
      │     │     └── 📂task
      │     └── 📂infra
      │     │     └── 📂db
      │     │     │     ├── 📂local // destination
      │     │     │     │     ├── 📂config
      │     │     │     │     ├── 📂entity
      │     │     │     │     └── 📂repository
      │     │     │     └── 📂titi // source
      │     │     │     │     ├── 📂config
      │     │     │     │     ├── 📂entity
      │     │     │     │     └── 📂repository
```
