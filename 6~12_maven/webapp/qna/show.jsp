<%@ page import="next.model.Question" %>
<%@ page import="next.model.Answer" %>
<%@ page import="java.util.List" %>
<%@ page import="next.model.User" %>
<%@ page import="next.controller.UserSessionUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp"/>

<div class="container" id="main">
    <div class="col-md-12 col-sm-12 col-lg-12">
        <%
            Question question = (Question) request.getAttribute("question");
            List<Answer> answers = (List<Answer>) request.getAttribute("answers");
            Object errorMessage = request.getAttribute("errorMessage");
            if(errorMessage != null){
        %>
            <div class="alert alert-danger" role="alert"><%=(String) request.getAttribute("errorMessage")%></div>
        <%}%>
        <div class="panel panel-default">
            <header class="qna-header">
                <h2 class="qna-title"><%=question.getTitle()%>
                </h2>
            </header>
            <div class="content-main">
                <article class="article">
                    <div class="article-header">
                        <div class="article-header-thumb">
                            <img src="https://graph.facebook.com/v2.3/100000059371774/picture"
                                 class="article-author-thumb" alt="">
                        </div>
                        <div class="article-header-text">
                            <a class="article-author-name"
                               href=<%="/user/profile?userId=" + question.getWriter()%>><%=question.getWriter()%>
                            </a>
                            <a class="article-header-time" title="퍼머링크"
                               href=<%="/question/show?questionId=" + question.getQuestionId()%>>
                                <%=question.getCreatedDate()%>
                                <i class="icon-link"></i>
                            </a>
                        </div>
                    </div>
                    <div class="article-doc">
                        <p><%= question.getContents()%>></p>
                    </div>
                    <div class="article-util">
                        <ul class="article-util-list">
                            <li>
                                <form class="form-delete" action="/question/update" method="GET">
                                    <input type="hidden" name="questionId" value=<%=question.getQuestionId()%>>
                                    <input type="hidden" name="writer" value=<%=question.getWriter()%>>
                                    <button class="link-delete-article" type="submit">수정</button>
                                </form>
                            </li>
                            <li>
                                <form class="form-delete" action="/question/delete" method="POST">
                                    <input type="hidden" name="questionId" value=<%=question.getQuestionId()%>>
                                    <button class="link-delete-article" type="submit">삭제</button>
                                </form>
                            </li>
                            <li>
                                <a class="link-modify-article" href="/">목록</a>
                            </li>
                        </ul>
                    </div>
                </article>

                <div class="qna-comment">
                    <div class="qna-comment-slipp">
                        <p class="qna-comment-count"><strong id ="countOfComment"><%=question.getCountOfComment()%>
                        </strong>개의 의견</p>
                        <div class="qna-comment-slipp-articles">

                            <% for(Answer answer : answers){%>

                            <article class="article">
                                <div class="article-header">
                                    <div class="article-header-thumb">
                                        <img src="https://graph.facebook.com/v2.3/1324855987/picture"
                                             class="article-author-thumb" alt="">
                                    </div>
                                    <div class="article-header-text">
                                        <a class="author" href=<%="/user/profile?userId=" + answer.getWriter()%>><%=answer.getWriter()%></a>
                                        <span class="time"><%=answer.getCreatedDate()%></span>
                                    </div>
                                </div>
                                <div class="article-doc comment-doc">
                                    <p><%=answer.getContents()%></p>
                                </div>
                                <div class="article-util">
                                    <ul class="article-util-list">
                                        <li>
                                            <a class="link-modify-article"
                                               href="/questions/413/answers/1405/form">수정</a>
                                        </li>
                                        <li>
                                            <form class="form-delete" action="/api/qna/deleteAnswer"
                                                  method="POST">
                                                <input type="hidden" name="answerId" value=<%=answer.getAnswerId()%>>
                                                <button type="submit" class="link-delete-article">삭제</button>
                                            </form>
                                        </li>
                                    </ul>
                                </div>
                            </article>
                            <%}%>

                            <div class="answerWrite">
                                <form name="answer" method="post">
                                    <input type="hidden" name="questionId" value=<%= question.getQuestionId()%>>
                                    <div class="form-group col-lg-4" style="padding-top:10px;">
                                        <%User user = UserSessionUtils.getUserFromSession(request.getSession());%>
                                        <label> 작성자 - <%=user.getName()%></label>
                                    </div>
                                    <div class="form-group col-lg-12">
                                        <textarea name="contents" id="contents" class="form-control" placeholder=""></textarea>
                                    </div>
                                    <button id= "submit-button"class="btn btn-success pull-right" type="submit">답변하기</button>
                                    <div class="clearfix"></div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="./answerTemplate.jsp"/>
<jsp:include page="../common/footer.jsp"/>