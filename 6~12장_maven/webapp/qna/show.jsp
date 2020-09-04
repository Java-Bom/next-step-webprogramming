<%@ page import="next.model.Answer" %>
<%@ page import="next.model.Question" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="kr">
<jsp:include page="../component/header.jsp"/>
<body>
<jsp:include page="../component/navigation.jsp"/>

<div class="container" id="main">
    <div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
        <div class="panel panel-default">
            <%
                Question question = (Question) request.getAttribute("question");
            %>
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
                            <a href="/users/92/kimmunsu" class="article-author-name"><%=question.getWriter()%>
                            </a>
                            <a href="/questions/413" class="article-header-time" title="퍼머링크">
                                <%=question.getCreatedDate()%>
                                <i class="icon-link"></i>
                            </a>
                        </div>
                    </div>
                    <div class="article-doc">
                        <%=question.getContents()%>
                    </div>
                    <div class="article-util">
                        <ul class="article-util-list">
                            <li>
                                <a class="link-modify-article" href="#">수정</a>
                            </li>
                            <li>
                                <form class="form-delete" action="#" method="POST">
                                    <input type="hidden" name="_method" value="DELETE">
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
                        <p class="qna-comment-count"><strong><%=question.getCountOfComment()%>
                        </strong>개의 의견</p>
                        <div class="qna-comment-slipp-articles">
                            <%
                                Collection<Answer> answers = (Collection<Answer>) request.getAttribute("answers");
                                for (Answer answer : answers) {
                            %>
                            <article class="article">
                                <div class="article-header">
                                    <div class="article-header-thumb">
                                        <img src="https://graph.facebook.com/v2.3/1324855987/picture"
                                             class="article-author-thumb" alt="">
                                    </div>
                                    <div class="article-header-text">
                                        <%=answer.getWriter()%>
                                        <div class="article-header-time"><%=answer.getCreatedDate()%>
                                        </div>
                                    </div>
                                </div>
                                <div class="article-doc comment-doc">
                                    <p><%=answer.getContents()%>></p>
                                </div>
                                <div class="article-util">
                                    <ul class="article-util-list">
                                        <li>
                                            <a class="link-modify-article"
                                               href="/api/qna/updateAnswer?answerId=<%=answer.getAnswerId()%>>">수정</a>
                                        </li>
                                        <li>
                                            <form class="form-delete" action="/api/qna/deleteAnswer" method="POST">
                                                <input type="hidden" name="answerId" value="<%=answer.getAnswerId()%>"/>
                                                <button type="submit" class="link-delete-article">삭제</button>
                                            </form>
                                        </li>
                                    </ul>
                                </div>
                            </article>
                            <%
                                }
                            %>
                            <div class="answerWrite">
                                <form name="answer" method="post">
                                    <input type="hidden" name="questionId" value="<%=question.getQuestionId()%>">
                                    <div class="form-group col-lg-4" style="padding-top:10px;">
                                        <input class="form-control" id="writer" name="writer" placeholder="이름">
                                    </div>
                                    <div class="form-group col-lg-12">
										<textarea name="contents" id="contents" class="form-control"
                                                  placeholder=""></textarea>
                                    </div>
                                    <input id="answerBtn" class="btn btn-success pull-right" type="submit"
                                           value="답변하기"/>
                                    <div class="clearfix"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/template" id="answerTemplate">
    <article class="article">
        <div class="article-header">
            <div class="article-header-thumb">
                <img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">
            </div>
            <div class="article-header-text">
                {0}
                <div class="article-header-time">{1}</div>
            </div>
        </div>
        <div class="article-doc comment-doc">
            {2}
        </div>
        <div class="article-util">
            <ul class="article-util-list">
                <li>
                    <a class="link-modify-article" href="/api/qna/updateAnswer/{3}">수정</a>
                </li>
                <li>
                    <form class="form-delete" action="/api/qna/deleteAnswer" method="POST">
                        <input type="hidden" name="answerId" value="{4}"/>
                        <button type="submit" class="link-delete-article">삭제</button>
                    </form>
                </li>
            </ul>
        </div>
    </article>
</script>

<jsp:include page="../component/script.jsp"/>
</body>
</html>
