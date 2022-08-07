getUserInfo(function (data) {
    if (data === "") {
        window.location = "/user/login.html";
    }
})
$(function () {
    $("#text").on('keyup', function () {
        let text = $("#text").val();
        $(".preview-box").html(marked(text));
        $("pre code").each(function () {
            hljs.highlightElement($(this)[0]);
        })
    })
    $("#article-submit").on('click', function () {
        let tags = []
        $("input[name='tags']:checked").each(function () {
            tags.push($(this).val())
        })
        if (tags.length <= 0) {
            $(".preview-box").empty().html("请至少选择一个标签");
            return;
        }
        $.post({
            url: "/article/create",
            data: {
                title: $("#input-title").val(),
                category: $("#input-category").val(),
                text: $("#text").val(),
                tags: tags
            },
            success: function (data) {
                $(".article").empty().html(data.message);
                window.location.href = "/";
            }
        })
    })
})