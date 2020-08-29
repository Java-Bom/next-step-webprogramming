<%@ page import="next.user.SessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="kr">
<jsp:include page="../component/header.jsp"/>
<body>
<jsp:include page="../component/navigation.jsp"/>


<div class="container" id="main">
    <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading"><h4>Profiles</h4></div>
            <div class="panel-body">
                <div class="well well-sm">
                    <div class="media">
                        <a class="thumbnail pull-left" href="#">
                            <img class="media-object" src="../images/80-text.png">
                        </a>
                        <div class="media-body">
                            <% SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("user");%>
                            <h4 class="media-heading"><%=sessionUser.getUserId()%>
                            </h4>
                            <p>
                                <a href="#" class="btn btn-xs btn-default"><span
                                        class="glyphicon glyphicon-envelope"></span>&nbsp;<%=sessionUser.getEmail()%>
                                </a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../component/script.jsp"/>
</body>
</html>