$(function () {
    load_data();
    let check = $(".check-btn");
    let edit = $(".edit-btn")
    let del = $(".remove-btn");
    check.each(function () {
        $(this).on('click', function () {
            window.open("/content/index.html?id=" + $(this).attr("data-index"), '_blank')
        })
    })
    edit.each(function () {
        $(this).on('click', function () {
            window.open("/static/article/edit.html?id=" + $(this).attr("data-index"), '_blank')
        })
    })
    del.each(function () {
        $(this).on('click', function () {
            $.post({
                url: "/admin/article/delete",
                data: {
                    id: $(this).attr("data-index")
                },
                success: function (data) {
                    $(".panel-title").append(data)
                    location.reload();
                }
            })
        })
    })
    $("#article-export").on('click', function () {
        location.href = "/admin/export/article"
    })
    $("#article-import").on('click', function () {
        $(".upload-window").css("display", "block");
    })
    $(".upload-close").on('click', function () {
        $(".upload-window").css("display", "none");
    })
})

function load_data() {
    $.post({
        url: "/admin/getArticle",
        async: false,
        success: function (data) {
            for (let article of data) {
                let html = "<tr>\n" +
                    "    <td>" + article.id + "</td>\n" +
                    "    <td>" + article.title + "</td>\n" +
                    "    <td>" + article.authorName + "</td>\n" +
                    "    <td>" + article.author + "</td>\n" +
                    "    <td>" + article.date + "</td>\n" +
                    "    <td>" + article.category + "</td>\n" +
                    "    <td><textarea readonly>" + article.text + "</textarea></td>\n" +
                    "    <td>" +
                    "    <div class='btn-group'>" +
                    "       <button type='button' data-index=" + article.id + " class='btn btn-primary check-btn'>查看</button>" +
                    "       <button type='button' data-index=" + article.id + " class='btn btn-info edit-btn'>修改</button>" +
                    "       <button type='button' data-index=" + article.id + " class='btn btn-danger remove-btn'>删除</button>" +
                    "    </div>" +
                    "    </td>\n" +
                    "</tr>";
                $(".article-table").append(html);
            }
        }
    })
}
