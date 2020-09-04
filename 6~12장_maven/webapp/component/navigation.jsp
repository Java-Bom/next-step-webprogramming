<%@ page import="next.user.SessionUser" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="../component/navigation-header.jsp"/>
<div class="navbar navbar-default" id="subnav">
    <div class="col-md-12">
        <div class="navbar-header">
            <a href="#" style="margin-left:15px;" class="navbar-btn btn btn-default btn-plus dropdown-toggle"
               data-toggle="dropdown"><i class="glyphicon glyphicon-home" style="color:#dd1111;"></i> Home <small><i
                    class="glyphicon glyphicon-chevron-down"></i></small></a>
            <ul class="nav dropdown-menu">
                <li><a href="../users/profile"><i class="glyphicon glyphicon-user"
                                                  style="color:#1111dd;"></i>
                    Profile</a></li>
                <li class="nav-divider"></li>
                <li><a href="#"><i class="glyphicon glyphicon-cog" style="color:#dd1111;"></i> Settings</a></li>
            </ul>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse2">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="/">Posts</a></li>
                <%
                    Optional<SessionUser> sessionUser = Optional.ofNullable((SessionUser) request.getSession().getAttribute("user"));
                    if (sessionUser.isPresent()) {
                %>
                <li><a href="javascript:void(0);" onclick="logout(); return false;" role="button">로그아웃</a></li>
                <li><a href="../users" role="button">개인정보수정</a></li>
                <%} else {%>
                <li><a href="../users/login-form" role="button">로그인</a></li>
                <li><a href="../users/form" role="button">회원가입</a></li>
                <%}%>
            </ul>
        </div>
    </div>
</div>