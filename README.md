# XXAlimi
2016-1 한성대학교 CapstoneProject
Team 어나니머시기

rssTest : Google Feed Api를 사용한 RSS 사용 예제 (1개의 jsp파일)

구현할 기능
-pc와 연동
-키워드 저장
-잠금화면에 표시
-추천 RSS
-많은 사용자가 등록한 사이트 추천
-로그인(네이버/페북)
-알림창 확인시 직접 사이트와 연결되서 보여주는 방법과 따로 내용을 읽어와서 보여주는 방법 두가지를 사용자가 설정
-등록한 사이트 기계학습을 통해 분류

현재 예상되는 문제점
- 긁어가는게 막힌 사이트는 새로운 API(ex. Jsoup)를 써서 긁어와야 된다 

실행 전 필요한 것
- Gradle
- (IDE 환경에서 필요) <a href="https://github.com/skyidelete/XXAlimi/wiki/Project-Lombok">Project Lombok</a> (https://projectlombok.org/)

실행 방법 : cmd환경 project폴더에서 gradle bootRun 실행
