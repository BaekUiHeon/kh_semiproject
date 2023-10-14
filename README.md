# BUH_BOARD
세미 프로젝트는 화려함보다는 과정에서 학습하고 있는 MVC 패턴에 대한 이해와
페이징, 좋아요, 대 댓글 등의 기능을 한 줄 한 줄 이해하면서 구현하는 데 중점을 두었습니다.

한 땀 한 땀 코드를 구현하면서 MVC 패턴에 대해 이해할 수 있었고,
Ajax를 포함한 자바스크립트를 많이 사용해 볼 수 있었습니다.
또한 페이징이나 대 댓글 등의 알고리즘을 이용한 코드 구현을 통해
코딩에 대한 재미와 자신감을 얻을 수 있었습니다.


<페이징 된 모습>
![image](https://github.com/BaekUiHeon/kh_semiproject/assets/135290607/a26a9629-1d60-4691-81b2-b8de365bbfb1)


<페이징 알고리즘 도식화>
![image](https://github.com/BaekUiHeon/kh_semiproject/assets/135290607/c64ef8f4-d369-4a03-b9b7-0c5942ac5c20)


1. Controller
  +1. 현재페이지 (default값 1)
  +2. 페이지당 게시물 수 10 (설계에 따른 고정값)
  +3. 페이지 번호갯수 5 (설계에 따른 고정값)
  +4. 현재페이지, 페이지당 게시물 수를 인자로하여 Dao,Service 함수 실행

2. Dao,Service
  +1. getTotal 함수로 총 게시물 수 구함.
  +2. 현재페이지 + 페이지당 게시물 수 + 총 게시물 수를 인자로 하여 데이터베이스로 부터 데이터 가져옴.
  +3. Controller로 총 게시물 수와 데이터를 반환

3. Controller
  +1. 총 게시물 수와, 데이터를 바탕으로 총 페이지번호, 시작 페이지 번호, 끝 페이지 번호를 구함.
  +2. 페이지 번호들 + 현재 페이지번호 + 게시물리스트를 가지고 JSP로 이동

<페이징 알고리즘 JSP단>
![image](https://github.com/BaekUiHeon/kh_semiproject/assets/135290607/68300b99-75ef-458c-b35f-4da0343710a2)




<Ajax 좋아요 알고리즘 도식화>
![image](https://github.com/BaekUiHeon/kh_semiproject/assets/135290607/775164e2-7271-496c-b9b4-29a67aa71f6d)





<Ajax로 구현한 대 댓글 적용 모습>
![image](https://github.com/BaekUiHeon/kh_semiproject/assets/135290607/833e6b46-b826-4e02-b529-8df420731b75)


![image](https://github.com/BaekUiHeon/kh_semiproject/assets/135290607/8a2d974d-5c40-4652-bd66-dee32301a2e9)

대 댓글 기능은 모두 Ajax로 구현하였습니다.
댓글 달기 클릭시 댓글을 작성할수 있는 <input>창이 생성되고 작성버튼을 클릭하면 Ajax로 작성한 댓글이 화면에 최신화 됩니다.
삭제또한 Ajax를 통해 구현하였습니다.








https://docs.google.com/presentation/d/1P7YOORMSCaUKaXtFcVNwvq3gJ6x-nBc-/edit?usp=sharing&ouid=115344925934371612567&rtpof=true&sd=true  (도식화된 PPT 12페이지 부터 도식화된 PPT와 설명이 있습니다.)
