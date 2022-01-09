# kyungpooktok

주제 : 경북대학교 학생을 대상으로한 익명 랜덤채팅서비스 

구성
프레임워크 : spring
데이터베이스 : mysql
주요기능 : 
  1. 유저관리
  2. 채팅
  3. 채팅방관리
  
  
초기작성한 플로우차트

///


1. 유저관리
  세부기능:
    1)로그인(인증상태-비회원)
      spring security를 통해 api를 작성,api를 통한 로그인
    
    2)회원가입(인증상태-비회원)
      데이터베이스 테이블생성-form으로 받은데이터 entity로 받아서 insert
      id(pk),username,password(securtiy로 hash적용),email,kakaotalkid,instarid,basetime,altertime
    3)회원정보수정(인증상태-회원)
      password,kakaotalkid,instarid를 수정,추가가능
    
    4)user인증에 따라 각 기능별로 권한부여(회원,비회원으로 권한을 나눔)
    
    5)로그아웃(인증상태-회원)
      
