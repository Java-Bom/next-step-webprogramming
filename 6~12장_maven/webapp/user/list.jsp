
<%@page import="java.util.*" %>
<%@page import="next.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp"/>

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
                    </td>
                    <td><%=user.getName()%>
                    </td>
                    <td><%=user.getEmail()%>
                    </td>
                    <td><a href="/user/update" class="btn btn-success" role="button">수정</a>
                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>