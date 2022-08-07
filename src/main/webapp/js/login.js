let status1 = false;
let status2 = false;

$(function () {
    $("#username").on('blur', function () {
        usernameCert(this);
    }).on('change', function () {
        $(this).removeClass("is-invalid");
        $(this).removeClass("is-valid");
    })
    $("#password").on('blur', function () {
        passwordCert(this)
    }).on('change', function () {
        $(this).removeClass("is-invalid");
        $(this).removeClass("is-valid");
    })
    let submitBtn = $("#submit");
    submitBtn.on('click', function () {
        usernameCert($("#username"));
        passwordCert($("#password"));
        if (status1 && status2) {
            doLogin();
        } else {
            $("#submit").addClass("is-invalid");
            $("#submit-feedback").addClass("invalid-feedback").text("请输入用户名和密码")
        }
    });
})

function doLogin() {
    let submit = $("#submit");
    let submit_feedback = $("#submit-feedback");
    $.post({
        url: "/user/login",
        data: {
            username: $("#username").val(),
            password: $("#password").val(),
            rememberMe:$("#rememberMe").is(":checked")
        },
        success: function (data) {
            submit_feedback.text(data.message);
            if (data.success) {
                submit.addClass("is-valid")
                submit_feedback.addClass("valid-feedback")
                setTimeout(function () {
                    let prevLink = document.referrer;
                    if ($.trim(prevLink) === '') {
                        location.href = '/';
                    } else {
                        location.href = prevLink;
                    }
                }, 500);
            } else {
                submit.addClass("is-invalid");
                submit_feedback.addClass("invalid-feedback");
            }
        },
        timeout: 2000,
        error: function () {
            submit_feedback.text("网络错误");
            submit.addClass("is-invalid");
            submit_feedback.addClass("invalid-feedback");
        }
    })
}

function usernameCert(el) {
    let username = $(el).val();
    if (username == null || username === "") {
        status1 = false;
        $(el).addClass("is-invalid");
        $("#username-feedback").text("用户名不能为空")
    } else {
        status1 = true;
        $(el).addClass("is-valid")
    }
}

function passwordCert(el) {
    let password = $(el).val();
    if (password == null || password === "") {
        status2 = false;
        $(el).addClass("is-invalid");
        $("#password-feedback").text("密码不能为空")
    } else {
        status2 = true;
        $(el).addClass("is-valid")
    }
}