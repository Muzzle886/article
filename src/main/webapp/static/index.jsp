<%@ page import="ga.muzzle.utils.MybatisUtils" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="ga.muzzle.mapper.UserMapper" %>
<%@ page import="ga.muzzle.pojo.User" %>
<%@ page import="java.util.List" %>
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
        <script src="${pageContext.request.contextPath}/static/js/user.js"></script>
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
                        所有用户
                        <button type="button" id="user-import" class="btn btn-primary btn-pill">导入</button>
                        <button type="button" id="user-export" class="btn btn-primary btn-pill">导出</button>
                    </h3>
                </div>
                <div class="panel-body">
                    <table class="user-table table table-bordered table-striped table-hover">
                        <tr>
                            <th>#</th>
                            <th>登录名</th>
                            <th>昵称</th>
                            <th>电话</th>
                            <th>邮箱</th>
                            <th>状态</th>
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
                欢迎访问用户导入页面。<br>
                文章导入请保证表格中有<strong>username,password,name,tel,email</strong>四个属性，并且处在<strong>第一张工作表</strong>的第一行位置<br>
                顺序不固定，文件可以是.xls、.xlsx文件，文件名不固定<br>
                示例: <img src="${pageContext.request.contextPath}/img/user.png" alt=""><br>
                <strong style="color: red">请务必保证每一个单元格的数据格式都是常规，不可出现数字的单元格</strong>
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
<div class="warning">警告<p>您确定要这么做吗？</p>
    <button id="confirm" class="btn btn-light">确定</button>
    <button id="cancel" class="btn btn-light">取消</button>
</div>
<div class="tips"></div>
</body>
<script>

</script>
</html>
