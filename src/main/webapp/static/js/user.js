$(function () {
    load_data();
    $(".check-btn").each(function () {
        $(this).on('click', function () {
            check_user($(this).attr("data-index"))
        })
    })
    $(".edit-btn").each(function () {
        $(this).on('click', function () {
            edit_user($(this).attr("data-index"))
        })
    })
    $(".ban-btn").each(function () {
        $(this).on('click', function () {
            warning(ban_user,$(this).attr("data-index"))
        })
    })
    $(".pardon-btn").each(function () {
        $(this).on('click', function () {
            pardon_user($(this).attr("data-index"))
        })
    })
    $(".remove-btn").each(function () {
        $(this).on('click', function () {
            warning(remove_user,$(this).attr("data-index"))
        })
    })
    $("#user-export").on('click', function () {
        location.href = "/admin/export/user"
    })
    $("#user-import").on('click', function () {
        $(".upload-window").css("display", "block")
    })
    $(".upload-close").on('click', function () {
        $(".upload-window").css("display", "none");
    })
})

function load_data() {
    $.post({
        url: "/admin/getUser",
        async: false,
        success: function (data) {
            for (let user of data) {
                let html;
                switch (user.status) {
                    case 0:
                        html = "<tr>\n" +
                            "    <td>" + user.id + "</td>\n" +
                            "    <td>" + user.username + "</td>\n" +
                            "    <td>" + user.name + "</td>\n" +
                            "    <td>" + user.tel + "</td>\n" +
                            "    <td>" + user.email + "</td>\n" +
                            "    <td>正常</td>\n" +
                            "    <td>" +
                            "    <div class='btn-group'>" +
                            "       <button type='button' data-index=" + user.id + " class='btn btn-primary check-btn'>查看</button>" +
                            "       <button type='button' data-index=" + user.id + " class='btn btn-info edit-btn'>修改</button>" +
                            "       <button type='button' data-index=" + user.id + " class='btn btn-warning ban-btn'>封禁</button>" +
                            "       <button type='button' data-index=" + user.id + " class='btn btn-danger remove-btn'>删除</button>" +
                            "    </div>" +
                            "    </td>\n" +
                            "</tr>"
                        break;
                    case 1:
                        html = "<tr class='table-warning'>\n" +
                            "    <td>" + user.id + "</td>\n" +
                            "    <td>" + user.username + "</td>\n" +
                            "    <td>" + user.name + "</td>\n" +
                            "    <td>" + user.tel + "</td>\n" +
                            "    <td>" + user.email + "</td>\n" +
                            "    <td>封禁</td>\n" +
                            "    <td>" +
                            "    <div class='btn-group'>" +
                            "       <button type='button' data-index=" + user.id + " class='btn btn-primary check-btn'>查看</button>" +
                            "       <button type='button' data-index=" + user.id + " class='btn btn-info edit-btn'>修改</button>" +
                            "       <button type='button' data-index=" + user.id + " class='btn btn-success pardon-btn'>解封</button>" +
                            "       <button type='button' data-index=" + user.id + " class='btn btn-danger remove-btn'>删除</button>" +
                            "    </div>" +
                            "    </td>\n" +
                            "</tr>"
                        break;
                }
                $(".user-table").append($(html));
            }
        }
    })
}

function warning(call, id) {
    $(".warning").css("display", "block")
    $("#confirm").click(function () {
        call(id);
        $(".warning").css("display", "none")
    })
    $("#cancel").on('click', function () {
        $(".warning").css("display", "none")
    })
}

function check_user(id) {
    console.log(id)
}

function edit_user(id) {

}

function ban_user(id) {
    $.post({
        url: "/admin/ban",
        data: {
            id: id,
        },
        success: function (data) {
            $(".tips").css("display", "block").text(JSON.stringify(data))
            setTimeout(function () {
                location.reload()
            }, 2000)
        }
    })
}

function pardon_user(id) {
    $.post({
        url: "/admin/pardon",
        data: {
            id: id,
        },
        success: function (data) {
            $(".tips").css("display", "block").text(JSON.stringify(data))
            setTimeout(function () {
                location.reload()
            }, 2000)
        }
    })
}

function remove_user(id) {
    $.post({
        url: "/admin/remove",
        data: {
            id: id,
        },
        success: function (data) {
            $(".tips").css("display", "block").text(JSON.stringify(data))
            setTimeout(function () {
                location.reload()
            }, 2000)
        }
    })
}