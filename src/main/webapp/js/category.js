$(function () {
    getArticle(function (data) {
        let category = new Set();
        for (let i = 0; i < data.length; i++) {
            category.add(data[i].category);
        }
        category.forEach(function (value) {
            let a = "<ul class='" + value + "'>" + value + "</ul>";
            $(".category-container").append($(a));
        })
        for (let i = 0; i < data.length; i++) {
            let b = "<li><div class='contents'><a href='/content/index.html?id=" + data[i].id + "'><div id='title'>" + data[i].title + "</div></a><div id='text'>" + data[i].text.substring(0, 200) + "..." + "</div></div></li>";
            $("." + data[i].category).append($(b));
        }
    })
})