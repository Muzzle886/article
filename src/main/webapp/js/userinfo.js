getUserInfo(function (data) {
    if (data === "") {
        window.location = "/login.html";
    }
})
$(function () {
    getUserInfo(function (user) {
        $(".name").html(user.name)
        $(".username").html(user.username)
        $(".tel").html(user.tel)
        $(".email").html(user.email)
    })
    getOwnArticle(function (data) {
        for (let i = 0; i < data.length; i++) {
            $(".own-article").append($("<div class='contents'>" +
                "<a href='/content/index.html?id=" + data[i].id + "'><div id='title'>" + data[i].title + "</div></a>" +
                "<button class='delete-btn' data-index='" + data[i].id + "'>删除</button>" +
                "<div id='text'>" + data[i].text.substring(0, 100) + "..." + "</div></div>"))
        }
        $(".delete-btn").each(function () {
            $(this).on('click', function () {
                let id = $(this).attr('data-index');
                if (confirm("确认删除？"))
                    $.post({
                        url: "/article/delete",
                        data: {
                            id: id
                        },
                        success: function (data) {
                            alert(data.message)
                            window.location.reload();
                        }
                    })
            })
        })
    })
    getCollectionArticle(function (data) {
        for (let i = 0; i < data.length; i++) {
            $(".collection-article").append($("<div class='contents'>" +
                "<a href='/content/index.html?id=" + data[i].id + "'><div id='title'>" + data[i].title + "</div></a>" +
                "<div id='text'>" + data[i].text.substring(0, 100) + "..." + "</div></div>"))
        }
    })
})

function getOwnArticle(callback) {
    $.post({
        url: "/article/user",
        success: function (data) {
            callback(data);
        }
    })
}

function getCollectionArticle(callback) {
    $.post({
        url: "/article/collection/articles",
        success: function (data) {
            callback(data);
        }
    })
}