$(function () {
    let status1 = false;
    let status2 = false;
    let status3 = false;
    $("#name").on('blur', function () {
        let name = $(this).val();
        let name_re = /^[\u4E00-\u9FA5A-Za-z0-9]+$/;
        if (name == null || name === "") {
            $(this).addClass("is-invalid");
            $("#name-feedback").text("昵称不能为空");
        } else if (!name_re.test(name)) {
            $(this).addClass("is-invalid");
            $("#name-feedback").text("昵称由汉字、字母、数字组成，不能包含下划线等特殊符号");
        } else {
            status1 = true;
            $(this).addClass("is-valid");
        }
    }).on('change', function () {
        $(this).removeClass("is-invalid");
        $(this).removeClass("is-valid");
    })
    $("#username").on('blur', function () {
        let username = $(this).val();
        let username_re = /^[a-zA-Z0-9]{4,15}$/;
        if (username == null || username === "") {
            $(this).addClass("is-invalid");
            $("#username-feedback").text("用户名不能为空")
        } else if (!username_re.test(username)) {
            $(this).addClass("is-invalid");
            $("#username-feedback").text("用户名长度在4~15之间，由字母和数字组成，不能包含下划线等特殊符号")
        } else {
            status2 = true;
            $(this).addClass("is-valid")
        }
    }).on('change', function () {
        $(this).removeClass("is-invalid");
        $(this).removeClass("is-valid");
    })
    $("#password").on('blur', function () {
        let password = $(this).val();
        let password_re = /^[a-zA-Z0-9]\w{5,18}$/;
        if (password == null || password === "") {
            $(this).addClass("is-invalid");
            $("#password-feedback").text("密码不能为空")
        } else if (!password_re.test(password)) {
            $(this).addClass("is-invalid");
            $("#password-feedback").text("密码长度在6~18之间，只能包含字母、数字和下划线")
        } else {
            status3 = true;
            $(this).addClass("is-valid")
        }
    }).on('change', function () {
        $(this).removeClass("is-invalid");
        $(this).removeClass("is-valid");
    })
    $("#tel").on('blur', function () {
        let tel = $(this).val();
        let tel_re = /^([1][3456789])\d{9}$/;
        if (tel == null || tel === "") {
            $(this).addClass("is-invalid");
            $("#tel-feedback").text("电话不能为空")
        } else if (!tel_re.test(tel)) {
            $(this).addClass("is-invalid");
            $("#tel-feedback").text("请输入正确的电话号码")
        } else {
            $(this).addClass("is-valid")
        }
    }).on('change', function () {
        $(this).removeClass("is-invalid");
        $(this).removeClass("is-valid");
    })
    $("#email").on('blur', function () {
        let email = $(this).val();
        let email_re = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if (email == null || email === "") {
            $(this).addClass("is-invalid");
            $("#email-feedback").text("邮箱不能为空")
        } else if (!email_re.test(email)) {
            $(this).addClass("is-invalid");
            $("#email-feedback").text("请输入正确的邮箱地址")
        } else {
            $(this).addClass("is-valid")
        }
    }).on('change', function () {
        $(this).removeClass("is-invalid");
        $(this).removeClass("is-valid");
    })
    $("#submit").on('click', function () {
        if (status1 && status2 && status3) {
            $("#submit").removeClass("is-invalid");
            doReg();
        } else {
            $("#submit").addClass("is-invalid");
            $("#submit-feedback").text("请将信息补充完整")
        }
    });
})

function doReg() {
    let login_status = $("#submit-feedback");
    $.post({
        url: "/user/reg",
        data: {
            name: $("#name").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            tel: $("#tel").val(),
            email: $("#email").val()
        },
        success: function (data) {
            login_status.text(data.message);
            if (data.success) {
                $("#submit").addClass("is-valid");
                $("#submit-feedback").removeClass("invalid-feedback").addClass("valid-feedback");
                setTimeout(function () {
                    let prevLink = document.referrer;
                    if ($.trim(prevLink) === '') {
                        location.href = '/';
                    } else {
                        location.href = prevLink;
                    }
                }, 500)
            } else {
                $("#submit").addClass("is-invalid")
            }
        },
        timeout: 2000,
        error: function () {
            login_status.text("网络错误！");
        }
    })
}