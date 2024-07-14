
## 프로젝트 일정
팀 프로젝트 기간 (20240513 ~ 20240627)


## <a name="section0" />목차
### 프로젝트 산출물(#section1)
### 팀원(#section2)
### 주제 선정(#section4)
### 사용 기술 & 도구(#section6)

<br>

### <a name="section1" />프로젝트 산출물

[프로젝트 발표.pdf](https://drive.google.com/file/d/1GPaqxSfGh2auJNmV_4I6UD4JXhf7xc5V/view?usp=sharing)
>
[UI 설게](https://www.figma.com/design/TDAFaHkbFCa1sngiLppZ0Z/pets?node-id=0-1&t=EmBOSQWk0Ypl8dBf-1)



### <a name="section2" />팀원 (#section0)

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

 
## <a name="section4" />기획 주제 선정 (20240215~202403)(#section0)

 **선정 이유**

**커뮤니티, 채팅 플랫폼**

- 실제 서비스 운영, 배포 경험을 위한, 결제 기능이 없는 단순 커뮤니티 서비스 구현하고자 하는 목표를 이루기 위해
- NLP 기술을 통한 검색엔진, 금칙어 시스템 구현을 통한 질 좋은 서비스 구축

**MSA 패턴 도입**

- 채팅, 커뮤니티, 관리자로 기능이 명확하게 나뉘고, 분리해서 운영할 수 있을 것 같아서 별개의 서버에 배포, 관리
- 장애 발생시 전체 서비스가 마비되는 불상사 방지, 코드 관리 및 기능 테스트 효율화
- 별개의 서버에서 발생하는 데이터 관리 및 관련되는 기능을 통한 통신 경험

            




## <a name="section6" />사용 기술 & 도구 (#section0)

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
