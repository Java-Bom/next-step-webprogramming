<%--
  Created by IntelliJ IDEA.
  User: jyami
  Date: 2020/09/08
  Time: 4:13 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
