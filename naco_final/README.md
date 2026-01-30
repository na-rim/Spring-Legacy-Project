Narim is coding (NACO)

- Spring MVC 기반
- week 2+3 과제와 함께, 학습 기록을 기록할 수 있는 기능을 추가하여 만들었습니다.

주요 기능
1. Task 
- Task 생성 / 조회 / 수정 / 삭제 (CRUD)
- 완료 여부 체크
- 생성 시간 기준 정렬

2. Problems
- 개인 문제/프로젝트 등록 및 관리
- 메모 및 풀이 시간 기록 (타이머 기능 사용 가능)

3. 사용자 인증
- 로그인 상태에 따른 페이지 접근 제어
- 관리자 페이지 (유저 목록 확인)

페이지 흐름
Login / Register
      ↓
   Task Board
      ↓  (개인 프로젝트 페이지로 이동)
   Problems
      ↓  (Task 페이지로 이동)
   Task Board
