<%@page import="next.model.User" %>
<%@page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="kr">
<jsp:include page="../component/header.jsp"/>
<body>
<jsp:include page="../component/navigation.jsp"/>

<div class="container" id="main">
    <div class="col-md-10 col-md-offset-1">
        <div class="panel panel-default">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>사용자 아이디</th>
                    <th>이름</th>
                    <th>이메일</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <%
                    Collection<User> users = (Collection<User>) request.getAttribute("users");
                    for (User user : users) {
                %>
                <tr>
                    <th scope="row">${status.count}</th>
                    <td><%=user.getUserId()%>
                    </td>
                    <td><%=user.getName()%>
                    </td>
                    <td><%=user.getEmail()%>
                    </td>
                    <td><a href="/users/update?userId=<%=user.getUserId()%>" class="btn btn-success"
                           role="button">수정</a>
                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- script references -->
<jsp:include page="../component/script.jsp"/>
</body>
</html>
