function load_data() {
    getUserInfo(function (data) {
        let s1 = $("#status-1");
        let s2 = $("#status-2");
        s1.empty();
        s2.empty();
        if (data != null && data !== "") {
            s1.html("<img src='/img/user.svg' alt=''>");
            s1.attr("href", "/user/userinfo.html");
            s2.html("<img src='/img/sign-out.svg' alt=''>");
            s2.attr("href", "javascript:void(0);");
            s2.on('click', function () {
                $.get({
                    url: "/user/signOut",
                    success: function () {
                        load_data()
                    }
                })
            })
            $(".name").text(data.name)
            $(".public").css("background", "#999999")
        } else {
            s1.html("登录");
            s1.attr("href", "/user/login.html");
            s2.html("注册");
            s2.attr("href", "/user/reg.html");
            $(".name").text("游客")
        }
    })
}

function getUserInfo(callback) {
    $.post({
        url: "/user/userInfo",
        success: function (data) {
            callback(data)
        }
    })
}

function getQueryVariable(variable) {
    let reg = new RegExp("(^|&)" + variable + "=([^&]*)(&|$)");
    let r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(decodeURI(r[2]));
    return null;
}