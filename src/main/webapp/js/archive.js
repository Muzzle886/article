$(function () {
    getArticle(function (data) {
        console.log(data);
        let month = new Set();
        for (let i = 0; i < data.length; i++) {
            let date = new Date(Date.parse(data[i].date.replace(/-/g, "/")));
            let str = date.getFullYear() + "-" + (date.getMonth() + 1);
            month.add(str);
        }
        month.forEach(function (value) {
            let a = "<ul class='" + value + "'>" + value + "</ul>";
            $(".archive-container").append($(a));
        })
        for (let i = 0; i < data.length; i++) {
            let date = new Date(Date.parse(data[i].date.replace(/-/g, "/")));
            let str = date.getFullYear() + "-" + (date.getMonth() + 1);
            let b = "<li><div class='contents'><a href='/content/index.html?id=" + data[i].id + "'><div id='title'>" + data[i].title + "</div></a><div id='text'>" + data[i].text.substring(0, 200) + "..." + "</div></div></li>";
            $("." + str).append($(b));
        }
    })
})