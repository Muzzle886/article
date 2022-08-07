$(function () {
    let id = getQueryVariable("id");
    $.post({
        url: "/article/id",
        data: {
            id: id,
        },
        success: function (data) {
            $("#input-title").val(data.title)
            $("#input-category").val(data.categoryId)
            $("#text").val(data.text)

            $(".preview-box").html(marked(data.text));
            $("pre code").each(function () {
                hljs.highlightElement(this);
            })
        }
    })
    $.post({
        url: "/admin/tag/id",
        data: {
            id: id
        },
        success: function (data) {
            $("input[name='tags']").val(JSON.parse(data))
        }
    })

    $("#article-submit").on('click', function () {
        let tags = [];
        $("input[name='tags']:checked").each(function () {
            tags.push($(this).val());
        })
        $.post({
            url: "/admin/edit",
            data: {
                id: id,
                title: $("#input-title").val(),
                categoryId: $("#input-category").val(),
                tags: tags,
                text: $("#text").val()
            },
            success: function (data) {
                alert(data)
            }
        })
    })
})

function getQueryVariable(variable) {
    let reg = new RegExp("(^|&)" + variable + "=([^&]*)(&|$)");
    let r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}