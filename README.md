
## 프로젝트 일정
팀 프로젝트 기간 (20240513 ~ 20240627)


<br>

## <a name="section1" />프로젝트 산출물

[프로젝트 발표.pdf](https://drive.google.com/file/d/1GPaqxSfGh2auJNmV_4I6UD4JXhf7xc5V/view?usp=sharing)
>
[UI 설게](https://www.figma.com/design/TDAFaHkbFCa1sngiLppZ0Z/pets?node-id=0-1&t=EmBOSQWk0Ypl8dBf-1)



## <a name="section2" />팀원

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


## <a name="section4" />사용 기술 & 도구

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
