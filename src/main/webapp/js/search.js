$(function () {
    let variable = decodeURI(getQueryVariable("keyword"));
    if (variable !== "null" && variable !== "" && variable != null) {
        $("#keyword").val(variable);
        search();
    }
    $("#submit").on('click', function () {
        search();
    })

    const input = $("#input-method");
    input.focus(function () {
        $(this).parent().css("box-shadow","0px 0px 15px 1.5px #95B8E7")
    })
    input.blur(function () {
        $(this).parent().css("box-shadow","none")
    })
    $("#keyword").focus(function () {
        $(this).parent().css("box-shadow","0px 0px 15px 1.5px #95B8E7")
    }).blur(function () {
        $(this).parent().css("box-shadow","none")
    })
})

function search() {
    $.post({
        url: "/article/search",
        data: {
            method:$("#input-method").val(),
            keyword: $("#keyword").val()
        },
        success: function (data) {
            const search1 = $("#search-result");
            search1.empty();
            if (data.length === 0) {
                search1.append("无结果");
            }
            for (let i = 0; i < data.length; i++) {
                let article = data[i];
                search1.append($(
                    "<div class='contents'>" +
                    "   <a href='/content/index.html?id=" + article.id + "'>" +
                    "       <div id='title'>" + article.title + "</div>" +
                    "   </a>" +
                    "   <div id='text'>" + article.text.substring(0, 100) + "...</div>" +
                    "</div>"
                ))
            }
        }
    })
}