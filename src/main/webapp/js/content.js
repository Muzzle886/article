$(function () {
    load_data();
    let favorite = $("#favorite img");
    let id = getQueryVariable("id");
    getArticleById(id, function (data) {
        formatDown(data);
        getFollowerCount(data.author);
        getFollowStatus(data.author, function (data) {
            if (data==="true") {
                $("#follow-btn").empty().html("取消关注")
                    .on('click', function () {
                        $.post({
                            url: "/user/unfollow",
                            data: {
                                id: id,
                            },
                            success: function (data) {
                                $(".tips").html(data.message);
                                window.location.reload();
                            }
                        })
                    })
            } else {
                $("#follow-btn").on('click', function () {
                    $.post({
                        url: "/user/follow",
                        data: {
                            id: id,
                        },
                        success: function (data) {
                            $(".tips").html(data.message);
                            window.location.reload();
                        }
                    })
                })
            }
        });
    })
    $('p code').each(function () {
        $(this).css({"background": "#eee", "border-radius": "3px", "padding": "1px"});
    });

    getTag(id, function (data) {
        let tags_box = $("#tags");
        for (let i = 0; i < data.length; i++) {
            if (i > 0) tags_box.append(",");
            tags_box.append(data[i])
        }
    })

    getCollectionCount(id)

    getUserInfo(function (data) {
        if (data !== null) {
            getCollectionStatus(id, function (data) {
                if (data === "true") {
                    favorite.attr("src", "/img/favorite-filling.svg")
                    favorite.attr("title", "取消收藏")
                }
            })
        }

        $("#favorite").on('click', function () {
            if (data === null) {
                alert("请先登录");
            } else {
                getCollectionStatus(id, function (data) {
                    if (data === "true") {
                        removeCollection(id)
                        favorite.attr("src", "/img/favorite.svg")
                    } else {
                        addCollection(id)
                        favorite.attr("src", "/img/favorite-filling.svg")
                        favorite.attr("title", "取消收藏")
                    }
                    getCollectionCount(id)
                })
            }
        })
    })
})

function formatDown(article) {
    let title_box = $("#title");
    let author_box = $("#author");
    let date_box = $("#date");
    let text_box = $("#text");
    title_box.html(article.title);
    author_box.html(article.authorName == null ? "[已注销]" : article.authorName);
    date_box.html(article.date);
    text_box.html(marked(article.text));
    document.querySelectorAll('pre code').forEach((el) => {
        hljs.highlightElement(el);
    });
}

function getArticleById(id, callback) {
    $.post({
        url: "/article/id",
        data: {
            id: id
        },
        success: function (data) {
            callback(data)
        }
    })
}

function getTag(id, callback) {
    $.post({
        url: "/article/tag",
        data: {
            id: id
        },
        success: function (data) {
            callback(data)
        }
    })
}

function getCollectionCount(id) {
    $.post({
        url: "/article/count",
        data: {
            id: id,
        },
        success: function (data) {
            $("#favorite-count").html(data)
        },
        timeout: 2000,
        error: function () {
            $("#favorite-count").text("网络错误")
        }
    })
}

function addCollection(articleId) {
    $.post({
        url: "/article/collection/add",
        data: {
            id: articleId
        },
        success: function (data) {
            console.log(data)
            console.log("添加收藏成功")
        }
    })
}

function removeCollection(articleId) {
    $.post({
        url: "/article/collection/remove",
        data: {
            id: articleId
        },
        success: function (data) {
            console.log(data)
            console.log("取消收藏成功")
        },
        timeout: 2000,
        error: function () {
            console.log("取消收藏失败")
        }
    })
}

function getCollectionStatus(articleId, callback) {
    return $.post({
        url: "/article/collection/status",
        data: {
            id: articleId
        },
        success: function (data) {
            callback(data)
        },
        timeout: 2000,
        error: function () {
            console.log("失败")
        }
    })
}

function getFollowStatus(id, callback) {
    $.post({
        url: "/user/follow/status",
        data: {
            id: id,
        },
        success: function (data) {
            callback(data)
        }
    })
}

function getFollowerCount(id) {
    $.post({
        url: "/user/follow/count",
        data: {
            id: id
        },
        success: function (data) {
            $("#follower").empty().html(data)
        }
    })
}
