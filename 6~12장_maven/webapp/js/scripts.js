$(document).ready(function () {/* jQuery toggle layout */
    $('#btnToggle').click(function () {
        if ($(this).hasClass('on')) {
            $('#main .col-md-6').addClass('col-md-4').removeClass('col-md-6');
            $(this).removeClass('on');
        } else {
            $('#main .col-md-4').addClass('col-md-6').removeClass('col-md-4');
            $(this).addClass('on');
        }
    });
});

String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined'
            ? args[number]
            : match
            ;
    });
};

function logout() {
    fetch("/users/logout", {
        method: "DELETE"
    })
        .finally(() => {
            window.location.href = '/';
        });
}

$answerBtn = document.querySelector("#answerBtn");
$answerBtn.addEventListener("click", addAnswer);
$(".qna-comment").on("click", ".form-delete", deleteAnswer);

function addAnswer(event) {
    event.preventDefault();
    const queryString = $("form[name=answer]").serialize();

    $.ajax({
        type: "POST",
        url: "/api/qna/addAnswer",
        data: queryString,
        dataType: "json",
        error: onError,
        success: onSuccessAddAnswer
    });
}

function deleteAnswer(event) {
    event.preventDefault();

    const deleteBtn = $(this);
    const queryString = deleteBtn.closest("form").serialize();

    $.ajax({
        type: "POST",
        url: "/api/qna/deleteAnswer",
        data: queryString,
        dataType: "json",
        error: onError,
        success: function () {
            deleteBtn.closest("article").remove();
        }
    });
}

function onError() {
    console.log("error")
}

function onSuccessAddAnswer(json, status) {
    const answerTemplate = $("#answerTemplate").html();
    const template = answerTemplate.format(json.answer.writer, json.answer.createdDate, json.answer.contents, json.answer.anserId);
    $(".qna-comment-slipp-articles").prepend(template);
}
