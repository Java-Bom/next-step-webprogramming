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

function addAnswer() {
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

function onError() {
    console.log("error")
}

function onSuccessAddAnswer(json, status) {
    const answerTemplate = $("#answerTemplate").html();
    const template = answerTemplate.format(json.writer, json.createdDate, json.contents, json.anserId);
    $(".qna-comment-slipp-articles").prepend(template);
}

function onSuccessDeleteAnswer(json, status) {

}
