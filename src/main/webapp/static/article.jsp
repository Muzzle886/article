<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>小型文章发布系统后台管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/index.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/shard.min.css">
    <script src="${pageContext.request.contextPath}/static/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/shards.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/marked.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/highlight.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/atom-one-dark.min.css">
        <script src="${pageContext.request.contextPath}/static/js/article.js"></script>
</head>
<body>
<div class="header">
    管理系统
</div>
<div class="main">
    <div class="left">
        <div class="nav">
            <ul>
                <li><a href="${pageContext.request.contextPath}/static" class="nav-item">用户管理</a></li>
                <li><a href="${pageContext.request.contextPath}/static/article.jsp" class="nav-item">文章管理</a></li>
                <li><a href="" class="nav-item">运行日志</a></li>
            </ul>
        </div>
    </div>
    <div class="view">
        <div class="user">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        所有文章
                        <button type="button" id="article-import" class="btn btn-primary btn-pill">导入</button>
                        <button type="button" id="article-export" class="btn btn-primary btn-pill">导出</button>
                    </h3>
                </div>
                <div class="panel-body">
                    <table class="article-table table table-bordered table-striped table-hover">
                        <tr>
                            <th>#</th>
                            <th>标题</th>
                            <th>作者</th>
                            <th>作者id</th>
                            <th>日期</th>
                            <th>分类</th>
                            <th>文本</th>
                            <th>操作</th>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="upload-window">
    <div class="upload">
        <div class="upload-container">
            <div class="tips-1">
                管理员你好<br>
                欢迎访问文章导入页面。<br>
                文章导入请保证表格中有<strong>title，author，date，category，text</strong>四个属性，并且处在<strong>第一张工作表</strong>>的第一行位置<br>
                顺序不固定，文件可以是.xls、.xlsx文件，文件名不固定<br>
                其中author属性请使用用户管理界面的用户id,category参照下图<br>
                <img src="${pageContext.request.contextPath}/img/category.png" alt=""><br>
                <strong style="color: red">请务必保证每一个单元格的数据格式都是常规，不可出现数字格式的单元格</strong>
            </div>
            <form action="${pageContext.request.contextPath}/admin/import/article" enctype="multipart/form-data"
                  method="post" class="form-horizontal">
                <label for="exampleInputFile" class="col-sm-2 control-label">File input</label>
                <input type="file" id="exampleInputFile" name="file">
                <input type="submit">
            </form>
        </div>
        <div class="upload-close">
            <img src="${pageContext.request.contextPath}/img/close.svg" alt="">
        </div>
    </div>
</div>
</body>
</html>