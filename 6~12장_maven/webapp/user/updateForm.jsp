<%--
  Created by IntelliJ IDEA.
  User: jyami
  Date: 2020/08/27
  Time: 3:43 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="next.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp"/>

<div class="container" id="main">
    <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-default content-main">

            <%User user = (User) request.getAttribute("user");%>

            <form name="question" method="post" action="/user/update">
                <div class="form-group">
                    <label for="userId">사용자 아이디</label>
                    <input type="hidden" class="form-control" id="userId" name="userId" value="<%=user.getUserId()%>"
                           placeholder="User ID">
                </div>
                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                </div>
                <div class="form-group">
                    <label for="name">이름</label>
                    <input class="form-control" id="name" name="name" value="<%=user.getName()%>" placeholder="Name">
                </div>
                <div class="form-group">
                    <label for="email">이메일</label>
                    <input type="email" class="form-control" id="email" name="email" value="<%=user.getEmail()%>"
                           placeholder="Email">
                </div>
                <button type="submit" class="btn btn-success clearfix pull-right">회원가입</button>
                <div class="clearfix"/>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>