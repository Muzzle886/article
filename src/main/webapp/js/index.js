$(function () {
    load_data();
    getCount(function (data) {
        $(".times").html(data.times);
        $(".article-count").text(data.article);
        $(".user-count").text(data.user)
    })
    getArticle(function (articles) {
        for (let article of articles) {
            let contents = "<div class='contents'><a href='/content/index.html?id=" + article.id + "'><div id='title'>" + article.title + "</div></a><div id='text'>" + article.text.substring(0, 200) + "..." + "</div></div>"
            $(".content-main").append($(contents));
        }
    })
})

function getArticle(callback) {
    $.post({
        async: false,
        url: "/article/getArticle",
        dataType: 'json',
        success: function (data) {
            callback(data);
        }
    });
}

function getCount(callback) {
    $.ajax({
        url: "/counts",
        success: function (data) {
            callback(data)
        }
    })
}