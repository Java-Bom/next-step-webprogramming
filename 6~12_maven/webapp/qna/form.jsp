<%@ page import="next.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp"/>

<div class="container" id="main">
    <div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
        <div class="panel panel-default content-main">
            <form name="question" method="POST" action="/question/create">
                <div class="form-group">
                    <%User user = (User) request.getAttribute("user");%>
                    <label for="title">작성자 : <%=user.getName()%>
                    </label>
                </div>
                <div class="form-group">
                    <label for="title">제목</label>
                    <input type="text" class="form-control" id="title" name="title" placeholder="제목"/>
                </div>
                <div class="form-group">
                    <label for="contents">내용</label>
                    <textarea name="contents" id="contents" rows="5" class="form-control" placeholder="내용"></textarea>
                </div>
                <button type="submit" class="btn btn-success clearfix pull-right">질문하기</button>
                <div class="clearfix" />
            </form>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>