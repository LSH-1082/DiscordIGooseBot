<div align='center'>
   
   # 🏃 디스코드 API를 이용한 근태 관리 봇
   디스코드 채팅창 명령어를 통해 출근 퇴근 시간을 데이터베이스에 저장하는 봇
   
  <img src="https://img.shields.io/badge/status-active-brightgreen" alt="Project Status">
  <img src="https://img.shields.io/badge/license-MIT-blue" alt="License">
  <img src="https://img.shields.io/github/languages/top/LSH-1082/DiscordIGooseBot" alt="Top Language">

   
</div>


## 📖 목차
1. [📋 프로젝트 소개](#-프로젝트-소개)
2. [✨ 주요 기능](#-주요-기능)
3. [🛠️ 기술 스택](#%EF%B8%8F-기술-스택)
4. [🧠 회고 및 배운점](#-회고-및-배운점)
5. [🤝 기여 방법](#-기여-방법)


## 📋 프로젝트 소개

디스코드 채팅을 이용한 근태 관리 봇으로, 동아리의 출근과 퇴근 시간을 간편하게 기록하고 관리할 수 있는 도구입니다. 
명령어를 통해 자동으로 근태를 기록하여, 관리 업무를 효율적으로 처리할 수 있도록 돕습니다.

## ✨ 주요 기능

1. **디스코드 채팅 명령어를 통한 사용**  
   - 디스코드 채팅창에 명령어를 통해 출퇴근 설정이 가능합니다  
2. **데이터베이스 연동**  
   - 데이터베이스에 연동이 되어있어 전체 인원의 출퇴근 목록을 볼 수 있습니다.  


## 🛠️ 기술 스택


**백엔드**
- ![Java](https://img.shields.io/badge/Java-007396?style=flat&logo=java&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-007396?style=flat&logo=java&logoColor=white)

**데이터베이스**
- ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)

**사용 도구**
- ![Git](https://img.shields.io/badge/Git-F05032?style=flat&logo=git&logoColor=white)

**외부 API**
- ![JDA (Java Discord API)](https://img.shields.io/badge/JDA%20(Java%20Discord%20API)-7289DA?style=flat&logo=discord&logoColor=white)


## 🧠 회고 및 배운점

- 출퇴근 시간을 어떻게 저장할지에 대한 기준이 명확하지 않아 데이터베이스 구조를 설계하는 데 어려움이 있었습니다. 또한 Spring Boot 없이 개발을 진행하면서 어떤 디자인 패턴을 적용해야 할지 판단하기 어려웠고 전체 프로젝트 구조에 일관성이 부족한 상태였습니다.

- 출근 시에는 시간을 기록하고 퇴근 시에는 해당 row의 퇴근 시간을 null에서 update하는 방식으로 출퇴근 시간 저장 구조를 설계하였습니다. 아울러 DAO와 DTO를 활용하고 MVC 패턴과 커맨드 패턴을 조합해 직접 프로젝트 구조를 정의하며 기능별 역할을 명확히 구분하였습니다. 이를 통해 프레임워크 없이도 구조적인 개발을 시도할 수 있었습니다.

- 데이터 설계와 프로젝트 아키텍처는 단순한 구현 이전에 먼저 고민하고 결정해야 할 중요한 요소임을 느꼈고 상황에 맞는 디자인 패턴을 선택하고 조합하는 감각을 키울 수 있었습니다. 향후에는 다양한 프로젝트에서 구조를 직접 설계하고 적절한 패턴을 적용해 보다 효율적으로 프로젝트를 진행해 나갈 계획입니다.


## 🤝 기여 방법

1. 이 레포지토리를 포크합니다.
2. 새로운 브랜치를 생성합니다. (git checkout -b feature/기능명)
3. 변경사항을 커밋합니다. (git commit -m '기능 추가 내용')
4. 브랜치를 푸시합니다. (git push origin feature/기능명)
5. 풀 리퀘스트를 생성합니다.
