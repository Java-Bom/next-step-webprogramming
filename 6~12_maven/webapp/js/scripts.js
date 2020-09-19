var t = document.getElementById('submit-button');
t.addEventListener('click', addAnswer);

function addAnswer(e) {
    e.preventDefault(); //submit 자동 동작을 막는다.
    const queryString = $("form[name=answer]").serialize(); // form 데이터를 자동으로 묶는다.
    console.log(queryString);

    $.ajax({
        type: "POST",
        url: "/api/qna/addAnswer",
        data: queryString,
        dataType: "json",
        error: onError,
        success: onSuccess
    });
}

function onSuccess(json, status) {
    var answerTemplate = $("#answerTemplate").html();
    var answer = json.answer;
    var template = answerTemplate.format(answer.writer, new Date(answer.createdDate), answer.contents, answer.answerId, answer.answerId);
    $(".qna-comment-slipp-articles").prepend(template);
    console.log(json);

    $.ajax({
        type: "POST",
        url: "/api/qna/addCountOfComment",
        data: "questionId=" + json.answer.questionId,
        dataType: "json",
        error: onError,
        success: onSuccessCount
    })

}

function onSuccessCount(json, status){
    console.log(json);
    $("#countOfComment").text(json.countOfComment);
}

function onError(xhr, status) {
    alert("error");
}

$(".qna-comment").on("click", ".form-delete", deleteAnswer);

function deleteAnswer(e){
    e.preventDefault(); //submit 자동 동작을 막는다.

    var deleteBtn = $(this);
    var queryString = deleteBtn.closest("form").serialize();

    $.ajax({
        type: "POST",
        url: "/api/qna/deleteAnswer",
        data: queryString,
        dataType: "json",
        error: onError,
        success: function (json, status) {
            deleteBtn.closest('article').remove();

            console.log(location.search.substring(1));
            $.ajax({
                type: "POST",
                url: "/api/qna/deleteCountOfComment",
                data: location.search.substring(1),
                dataType: "json",
                error: onError,
                success: onSuccessCount
            })
        }
    });
}

String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined'
            ? args[number]
            : match
            ;
    });
};

$(document).ready(
    function () {/* jQuery toggle layout */
        console.log("hello")
    });