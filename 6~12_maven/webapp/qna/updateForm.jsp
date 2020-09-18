<%@ page import="next.model.Question" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp"/>

<div class="container" id="main">
    <div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
        <div class="panel panel-default content-main">
            <form name="question" method="post" action="/question/update">
                <%Question question = (Question) request.getAttribute("question");%>
                <%String writer = (String) request.getAttribute("writer"); %>
                <label for="title">작성자 : <%=writer%>
                    <input type="hidden" name="writer" value=<%=writer%>>
                    <input type="hidden" name="questionId" value=<%=question.getQuestionId()%>>

                    <div class="form-group">
                        <label for="title">제목</label>
                        <input type="text" class="form-control" id="title" name="title" placeholder="제목"
                               value=<%=question.getTitle()%>/>
                    </div>
                    <div class="form-group">
                        <label for="contents">내용</label>
                        <textarea name="contents" id="contents" rows="5"
                                  class="form-control"><%=question.getContents()%></textarea>
                    </div>
                    <button type="submit" class="btn btn-success clearfix pull-right">수정하기</button>
                    <div class="clearfix"/>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>