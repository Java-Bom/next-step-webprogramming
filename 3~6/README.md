# Next-step-webprogramming - 3~6장

자바 웹 프로그래밍 Next-step 3~6장 레포 입니다. 
아래의 내용과 앞으로 진행할 모든 과정의 출처는 박재성님의 '자바 웹 프로그래밍 Next Step' 입니다.

## 개발환경 셋팅

- 박재성님의 https://github.com/slipp/web-application-server 프로젝트 master 브랜치의 프로젝트 구조와 같습니다.
- 책 과정에서 재성님은 eclipse와 maven환경에서 진행하였습니다.
- javabom 스터디에선 intellij와 gradle 환경에서 진행합니다.
- 3~6장 재성님의 모든 코드는 https://github.com/slipp/web-application-server 프로젝트의 각 브랜치에 있습니다.

## 웹 서버 시작 및 테스트

- webserver.WebServer 는 사용자의 요청을 받아 RequestHandler에 작업을 위임하는 클래스입니다.
- 사용자 요청에 대한 모든 처리는 RequestHandler 클래스의 run() 메서드가 담당합니다.
- WebServer를 실행한 후 브라우저에서 [http://localhost:8080으로](http://localhost:8080으로/) 접속해 "Hello World" 메시지가 출력되는지 확인합니다.

