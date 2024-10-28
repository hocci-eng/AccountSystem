# 📚개인 프로젝트 - Account System
- 계좌(생성, 해지, 확인) 및 거래(잔액 사용, 잔액 사용 취소, 거래 조회)의 개념을 이용한 기능 구현
- Implementation of functions using the concepts of accounts (create, delete, verify) and transactions (balance use, balance use cancel, transaction verify)
  <br>

# 🛠️ 기술 스택
- Language : `Java`
- Database : `H2 DB`
- JDK : `OpenJDK 23`
- API 기능 : `계좌 관련 API`, `거래(Transaction) 관련 API`
- Library : `Spring data jpa`, `Spring`, `Embedded redis`, `Jackson`, `Lombok`, `mockito`, `Junit5`
  <br>

# 👀 프로젝트 기능
## ✅ 계좌 관련 API
### 1) 계좌 생성
- 계좌번호는 순차 증가 방식으로 생성
- 계좌번호는 10자리 랜덤 숫자
- 결과
  - 실패
    - 사용자 없는 경우
    - 계좌가 10개를 초과한 경우
  - 성공
    - 응답 정보 : 사용자 아이디, 생성된 계좌 번호, 등록일시(LocalDateTime)
![image](https://github.com/user-attachments/assets/894e4d60-0cb2-471f-9dc0-58f76fa8686b)

<br>

### 2) 계좌 해지
- 결과
  - 실패
    - 사용자 없는 경우
    - 사용자 아이디와 계좌 소유주가 다른 경우
    - 계좌가 이미 해지 상태인 경우
    - 잔액이 있는 경우
  - 성공
    - 응답 정보 : 사용자 아이디, 계좌번호, 해지일시
![image](https://github.com/user-attachments/assets/b05e6fc9-55da-471b-a5c5-eb1b86f5f1ad)

<br>

### 3) 계좌 확인
- 결과
  - 실패
    - 사용자 없는 경우 실패 응답
  - 성공
    - 응답 : (계좌번호, 잔액) 정보를 Json list 형식으로 응답
![image](https://github.com/user-attachments/assets/ee1af548-bc10-467c-8a4a-62cddeabc83d)

  <br>

## ✅ 거래(Transaction) 관련 API
### 1) 잔액 사용
- 결과
  - 실패
    - 사용자 없는 경우
    - 사용자 아이디와 계좌 소유주가 다른 경우
    - 계좌가 이미 해지 상태인 경우
    - 거래금액이 잔액보다 큰 경우
    - 거래금액이 너무 작거나 큰 경우
  - 성공
    - 응답 : 계좌번호, transaction_result, transaction_id, 거래금액, 거래일시
![image](https://github.com/user-attachments/assets/dec1e645-d771-4848-945a-2cf3c71fe707)

<br>

### 2) 잔액 사용 취소
- 결과
  - 실패
    - 원거래 금액과 취소 금액이 다른 경우
    - 트랜잭션이 해당 계좌의 거래가 아닌 경우
  - 성공
    - 응답 : 계좌번호, transaction_result, transaction_id, 취소 거래금액, 거래일시
![image](https://github.com/user-attachments/assets/2a18cf75-3b41-41ef-b4da-69d0634b2b67)

<br>

### 3) 거래 확인
- 성공한 거래 및 실패한 거래 확인
- 결과
  - 실패 : 해당 transaction_id 없는 경우
  - 성공
    - 응답 : 계좌번호, 거래종류(잔액 사용, 잔액 사용 취소), transaction_result, transaction_id, 거래금액, 거래일시
![image](https://github.com/user-attachments/assets/996551aa-d2f3-4fb6-810f-14170111ce93)

