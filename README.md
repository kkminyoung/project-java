# Java Reservation System

### 구조
```
FinalProject
├─ .settings
├─ bin
├─ gui_imgs                                  # GUI 화면구성에 필요한 이미지 파일 폴더
├─ serverDB                                  # 스터디룸 정보, user 정보, 예약자정보, 대기자정보를 담은 데이터베이스
└─ src                                       # 작성한한 JAVA 소스코드
      ├─ main                                # 실행할 부분
      │    ├─ RunClient.java                 # Client 실행
      │    └─ RunServer.java                 # Server 실행
      ├─ client                              # 클라이언트 
      │    ├─ ClientController.java          
      │    ├─ ClientSender.java              
      │    ├─ SessionController.java         # 로그인한 유저의 세션관리
      │    └─ view                           # FrontEnd (GUI)
      │         ├─ LoginView.java              # 초기 로그인화면
      │         ├─ ManagerMainView.java        # 스터디룸 업주용 메인화면
      │         ├─ MenuManagementView.java     # 스터디룸 업주의 스터디룸 관리 화면
      │         ├─ OrderMenuView.java          # 일반사용자의 스터디룸 예약 화면
      │         ├─ SelectMenuView.java         # 일반 사용자의 스터디룸 종류 선택 화면
      │         ├─ SignUpManagerView.java      # 스터디룸 업주의 회원가입화면
      │         ├─ SignUpUserView.java         # 일반 사용자의 회원가입화면
      │         ├─ SignUpView.java             # 회원가입화면(업주, 일반 사용자 선택)
      │         └─ UserMainView.java           # 일반사용자용 메인화면
      ├─ server
      │    ├─ controller                     # 서버 controller 관리 코드
      │    │    ├─ ServerGETController.java        
      │    │    └─ ServerPOSTController.java  
      │    ├─ model                          # 서버 모델 관리 코드
      │    │    ├─ ServerGETmodel.java        
      │    │    └─ ServerPOSTmodel.java
      │    ├─ DBsetting.java                 # 사용자, 스터디룸리스트, 대기자리스트, 예약리스트 세팅
      │    ├─ MyServer.java                  # 소켓서버 run 함수 구현
      │    ├─ Protocol.java                  # 프로토콜
      │    ├─ ServerIP.java                  # Server IP 지정(localhost로 해놓음) 
      │    └─ ServerReceiver.java            # Server의 패킷을 받는부분 
      └─ util                                # 패킷, 예약, 예약리스트, 스터디룸, 스터디룸 리스트, Session 정보, 사용자정보 등 관리
```

### 실행방법
```
main 폴더의 RunServer.java, RunClient.java를 실행하면 된다.
```
