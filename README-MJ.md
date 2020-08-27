### JSP 사용시 주의점

인코딩 문제가 발생했었다. jsp 사용시 꼭 contentType을 추가해 주어야 한다.

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
```

![jsp_encoding](./resource/jsp_encoding.png)



### setCookie

```java
response.addHeader("Set-Cookie", "JSESSIONID="+UUID.randomUUID());
```

했을 때 실제로 브라우저(클라이언트)에서 Cookie가 설정되도록 Set-Cookie 설정

<img src="./resource/JsessionIdResponse.png" alt="JsessionIdResponse" style="zoom:50%;" />

이후 새로고침 했을 때 RequestHeader에 Cookie가 들어가는지 확인

![JsessionIdRequest](./resource/JsessionIdRequest.png)



