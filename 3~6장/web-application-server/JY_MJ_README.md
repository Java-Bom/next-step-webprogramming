


2020.04.19 

form Data 로 넘어오는 body 데이터는 br.readLine() 으로는 읽히지 않는다.
br.read() 로 byte 를 읽어야 넘어오는군 !!!!




```java
private void response302Header(DataOutputStream dos){
        try {
            dos.writeBytes("HTTP/1.1 302 Found \r\n");
            dos.writeBytes("Location: /index.html\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
```

sockerServer에서 writeBytes만 해주었을 때 브라우저에서 자동으로 redirect를 해준다. 왜 그렇게 해주는지 궁금해서 책을 찾아보니

Socket에서는, Http로 보낼 데이터만 써주며, 해당 데이터를 받아서 Redirect 처리를 하는 것은 브라우저이다.

브라우저에서는 socket(TCP 계층) 에서 HTTP 프로토콜 규약에 맞게 넘어 온 값을 해석하여, 요청을 받아 응답을 뿌려주는 것이다.

실제로 저 HTTP/1.1 400 으로 보내면, 400 에러가 뜨며, HTTP/1.2323 와 같은 존재하지 않은 포맷의 http header 값을 넘겨주면,

브라우저에서 소켓에서 넘어온 데이터를 해석하지 못했다.  

> 와 너무 신기하다 >< 대박대박 

Socket이 해당 Header를 브라우저로 응답하면, 브라우저는 내용을 파악해서 알아서 알아서 해줌
302애 Location 읽어서 알아서 Redirect 해줌 



