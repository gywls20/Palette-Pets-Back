
## 📅 프로젝트 일정
팀 프로젝트 기간 (20240513 ~ 20240627)
>
팀 분리 이후 개인으로 프로젝트 개선(20240628 ~ )

## <a name="section0" />🚀바로가기
### [1.💼프로젝트 산출물](#section1)
### [2.🧑🏻프로젝트 멤버](#section2)
### [3.📱기획 주제 선정](#section4)
### [4.🚨트러블 슈팅](#section5)
### [5.⚙️Stack & Tool](#section6)

<br>

### <a name="section1" />💼프로젝트 산출물
✔️ **프로젝트 산출물** 

[프로젝트 발표.pdf](https://drive.google.com/file/d/1GPaqxSfGh2auJNmV_4I6UD4JXhf7xc5V/view?usp=sharing)
>
[간단한 시스템 아키텍처](https://www.figma.com/board/I3S3GViSuWVW6f1XCSudlT/Final-Architecture?node-id=0-1&t=3LeZjxDonI4eDf9b-0)
>
[UI 설게](https://www.figma.com/design/TDAFaHkbFCa1sngiLppZ0Z/pets?node-id=0-1&t=EmBOSQWk0Ypl8dBf-1)



### <a name="section2" />🧑🏻 프로젝트 멤버 [🔝](#section0)

<table>
  <tr>
    <td align="center"><a href="https://github.com/Kim-soung-won"><b>승원</b></a></td>
    <td align="center"><a href="https://github.com/Ryuwongeun"><b>원근</b></a></td>
    <td align="center"><a href="https://github.com/gywls20"><b>혜경</b></a></td>
    <td align="center"><a href="https://github.com/dl11911"><b>승훈</b></a></td>
  </tr>
  <tr>
    <td align="center"><a href="https://github.com/gyuchanlee"><b>규찬</b></a></td>
    <td align="center"><a href="https://github.com/gywls20"><b>효진</b></a></td>
    <td align="center"><a href="https://github.com/jyc961020"><b>용철</b></a></td>
    <td align="center"><a href="https://github.com/cuscus8"><b>훈민</b></a></td>
  </tr>
</table>

 
## <a name="section4" />📱기획 주제 선정 (20240215~202403) [🔝](#section0)

🧐 **선정 이유**

**커뮤니티, 채팅 플랫폼**

- 실제 서비스 운영, 배포 경험을 위한, 결제 기능이 없는 단순 커뮤니티 서비스 구현하고자 하는 목표를 이루기 위해
- 기존 API 통신이 아닌, 연결형 Socket 통신 서비스 구현 경험에 대한 학습
- NLP 기술을 통한 검색엔진, 금칙어 시스템 구현을 통한 질 좋은 서비스 구축

**MSA 패턴 도입**

- 채팅, 커뮤니티, 관리자로 기능이 명확하게 나뉘고, 분리해서 운영할 수 있을 것 같아서 별개의 서버에 배포, 관리
- 장애 발생시 전체 서비스가 마비되는 불상사 방지, 코드 관리 및 기능 테스트 효율화
- 별개의 서버에서 발생하는 데이터 관리 및 관련되는 기능을 통한 통신 경험

**Redis Cache를 포함한 NoSQL 추가**

- 많은 양의 실시간 데이터 조회 및 쓰기가 필요한 시스템에 RDBMS는 너무 느린 성능을 보일 것으로 예상
- 데이터 캐싱을 통한 조회 성능 개선 및, 일괄 쓰기를 통한 쓰기 비용 감소
- TTL 데이터를 활용한 조회수, 글쓰기 등의 횟수 제한을 통한 쓰기 작업 처리율 제한



## <a name="section5" />🚨트러블 슈팅 [🔝](#section0)

---

### 트러블1. [SSE 서버 분리를 통한 응답 성능 개선]

🚨 **문제 배경**

알림 기능을 수행하기 위한 SSE(Server Sent Event) Connect이 자주 발생하며 기본 API 응답 요청의 지연 발생
특정 조회기능을 연속해서 클릭(광클)할 시 페이지가 멈춰버리는 현상도 발생했다.
React 상태관리 미흡으로 인한 SSE Connection 문제로 파악했으나, 리액트 상태관리를 전부 개선하기에는 프로젝트 기한이 촉박했고 React 활용에 대한 경험 또한 부족했다.

⭐️ **해결 방법**

그래서 Main Community Server에 적재된 SSE 알림 기능을 실시간 채팅 서버로 이관하였다.
이 결정을 내린 근거는, Chatting Server는 채팅방에 있을 때만 Socket Connection이 이뤄졌고,
Chatting 자체가 차지하는 서버 Resource가 크지 않다고 생각해 같은 연결형 기능인 SSE를 함께 다뤄도 좋을 것 같다고 판단했다.
우선 가장 비용이 많이 드는 RDBMS 쓰기, 조회 작업이 많이 발생하는 Main 커뮤니티 서버로 부터 분리해야한다는 목표가 명확했다.

🤩 **해당 경험을 통해 알게된 점**

서버를 분리해서 얻는 이점에 대해 한번 더 경험할 수 있었다. 또 SSE와 같은 연결형 기능의 Connection을 잘 컨트롤해주어야 활용할 수 있다는 사실을 다시한번 느낄 수 있었다.
프론트 단계에서 요청을 잘 컨트롤하여 한번만 연결하고 그 상태를 지속하면 서버 분리보다는 간단하게 문제가 해결될 것이라고 생각된다. 추후에 해당 방안에 대해서도 시도해볼 예정에 있다.

---



## <a name="section6" />⚙️Stack & Tool [🔝](#section0)

### 프론트엔드
- JavaScript
- React
- Redux

### UI
- Figma
- MUI
- TailWinds

### 백엔드  
- Java, Kotlin
- Spring Boot3
- JPA
- QueryDSL
- JWT

### DBMS 
- MySQL8
- Redis

### DevOps
- NCP
- Docker
- Jenkins
- sonarqube

### 커뮤니케이션
- Notion
- Figma



# 냥가왈부 백엔드 

SpringBoot 3.2.5 버전 사용

- spring boot 기반
- JPA, QueryDsl, Mysql 로 DB 관리
- API 서버 구현

### 브랜치 규칙

1. main : 초기 프로젝트 기본 설정 -> 오류, 프로젝트 롤백 시 사용할 스냅샷
2. dev : 각자의 브랜치에서 병합해서 진행할 최종 개발 산출물 브랜치
3. 각자 이름 브랜치 : 개인의 작업물을 먼저 이곳에 올리고 그다음에 dev로 풀 리퀘스트 Go

### 커밋메세지 규칙
~~~
  < type >: 제목       //헤더필수
                      //빈행으로 구분함
  본문

~~~
|타입이름|내용
|------|---|
feat|	새로운 기능에 대한 커밋
fix|	버그 수정에 대한 커밋
connect|	외부 연결 (CI/CD 연결하기) 
question|   질문
refactor|	코드 리팩토링에 대한 커밋
test|	테스트 코드 수정에 대한 커밋

EX.
 ~~~
 feat: 사용자 프로필 페이지 추가
 
 - 사용자 프로필 페이지 및 라우팅 구현
 - 프로필 정보를 보여주는 프로필 카드 컴포넌트 구현
 - 프로필 수정 기능 구현
 ~~~
