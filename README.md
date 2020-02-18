

# 취뽀냥이  Getting-job-management-service



// 이쁜 그림 넣기

내 취업 정보(정보, 자기소개서, 인터뷰)를 관리하는 페이지 입니다.

## 목차

- 소개
- 기획 의도
- 시스템 아키텍쳐
- ERD
- 에필로그



##  소개

  [![alt text](https://img.shields.io/badge/@vue--cli-v4.1.2-green.svg?longCache=true&style=flat-square)](https://github.com/sindresorhus/query-string) [![alt text](https://img.shields.io/badge/react-v16.4.2-orange.svg?longCache=true&style=flat-square)](https://github.com/facebook/react/)  



## 팀 소개

| 팀원         | 역할                                                         |
| ------------ | ------------------------------------------------------------ |
| 박권응       |                                                              |
| 백민주       | Vue와 Vuetify 활용한 페이지 구성, Vuex 활용, Axios를 이용한 백엔드와 통신 |
| 윤규희       |                                                              |
| 이다슬(팀장) |                                                              |



## 기획의도

취업을 준비하는 사람 중 한 명으로서 자기소개서를 키워드별로 정리해주는 취업플랫폼이 있다면 편하지 않을까 생각을 해 보았다. 

1) 정보의 분산

개인적으로 면접 후기는 네이버 블로그에, 자기소개서는 컴퓨터 내 폴더에, 이력 정보는 엑셀 파일 관리하고 있으나 정보가 분산되어 있어 불편함을 느꼈다. 

2) 불편한 검색 기능

자기소개서를 쓸 때마다 기업이 요구하는 키워드에 맞게 글을 쓰기 위해 컴퓨터 자체의 검색기능을 활용해 불편한 키워드 검색을 할 수 밖에 없었다. 

3) 글자수 파악의 어려움

내가 쓴 자기소개서의 글자를 직접 적어놓지 않는 이상 그 자기소개서 문항의 글자수를 파악하기 어려웠다. 

**따라서 자기소개서를 키워드로 관리해주면서 글자수까지 표시되어 자기소개서를 쉽게 쓸 수 있는, 더 나아가 이력과 면접후기 관리까지 할 수 있는 private 사이트를 기획하게 되었다. **

<사진 추가>

## Frontend - Vue.js

삼성 소프트웨어 아카데미에서 배운 Vue를 활용해 프로젝트를 진행했다. 

### 컴포넌트 구성

Drawer(사이드바), Education components, Interview components, image banner, career components, navbar, resume components, signup modal, login modal

### 페이지 구성

```
Views
|-----Homepage.vue
|-----MainPage.vue
|-----InterviewPage.vue
|-----MyPage.vue
|-----ResumePage.vue
|-----Teampage.vue
|-----Userinfo.vue
|-----AdminPage.vue
```

