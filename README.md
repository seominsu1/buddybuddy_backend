# 🤿 buddybuddy
buddybuddy 프로젝트는 Api 서버 구축 프로젝트입니다. 1인 프로젝트로 진행되었습니다.   
불편했던 이슈를 기술적인 방법을 통해 해결하고자 시작한 프로젝트입니다.

## 기획   
▶︎ [상세 안내](https://polydactyl-search-400.notion.site/PRD-Product-Requirements-Document-442465cd99e44e0cbd42f4287767a490)   
- 불편 했던 이슈 → 국내 다이빙 풀 동반 입장자를 찾는 서비스의 부재 및 다이빙 장비 선순환
- 해결 방안 → 동반 입장자를 구할 수 있고 다이빙 장비를 경매할 수 있는 플랫폼 구축

## 기술 스택   
- Back-end
  - Java 17
  - Spring Boot 3.1.0
  - Spring Data Jpa
  - QueryDsl
  - Spring Security
  - Jwt
  - WebSocket
  - stomp
- DevOps
  - Jenkins
  - Github Webhook
  - Docker
  - Aws Ec2, Rds
- DB
  - Mysql
  - Redis(cache)
- ETC
  - Swagger

## CI/CD   
▶︎ [상세 안내](https://polydactyl-search-400.notion.site/CI-CD-9276ca76e76f49839ce8c7d9b9ddc76e)   
![](https://polydactyl-search-400.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F42cc9b13-139d-4025-8a1b-39e3a8b906ff%2F57d49eb5-d3aa-44cb-89d6-0471d7c4eb86%2FUntitled.png?table=block&id=90b2bb8e-e221-47c4-bf5f-2483e414f1f1&spaceId=42cc9b13-139d-4025-8a1b-39e3a8b906ff&width=2000&userId=&cache=v2)

## ERD
![](https://polydactyl-search-400.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F42cc9b13-139d-4025-8a1b-39e3a8b906ff%2Ff6a634a8-dcb1-443a-8ec2-a77d585e443b%2FUntitled.png?table=block&id=77353c84-0971-419a-b902-84849bcc1102&spaceId=42cc9b13-139d-4025-8a1b-39e3a8b906ff&width=2000&userId=&cache=v2)

## 경매 서비스
▶︎ [상세 안내](https://polydactyl-search-400.notion.site/2865b901850e490a8579c4f3fd6c6f39)   
- 경매 데이터 흐름   

![](https://polydactyl-search-400.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F42cc9b13-139d-4025-8a1b-39e3a8b906ff%2Fdfa5fb23-2428-4676-9981-88c5306b93aa%2FUntitled.png?table=block&id=78d3551a-64dc-49e8-ba8b-1438f4df7a3d&spaceId=42cc9b13-139d-4025-8a1b-39e3a8b906ff&width=1980&userId=&cache=v2)

- 경매 서비스 화면 (ui 는 크게 신경 쓰지 못하였습니다.)   

![](https://polydactyl-search-400.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F42cc9b13-139d-4025-8a1b-39e3a8b906ff%2F9da347dd-4aaf-40b6-b63c-b3b204d6c203%2FUntitled.png?table=block&id=93c6ffa4-9678-4251-95e4-2c2c17a95302&spaceId=42cc9b13-139d-4025-8a1b-39e3a8b906ff&width=1790&userId=&cache=v2)

## 관련 channel
▶︎ [프로젝트 문서](https://polydactyl-search-400.notion.site/BuddyBuddy-310bbc27af98417e869a64a5a07b352b)   
▶︎ [발생했던 문제와 해결책 정리](https://velog.io/@seominsu1/series/buddybuddy)


