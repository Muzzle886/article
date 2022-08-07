<%@ page import="jakarta.servlet.http.Cookie" %><%--
  Created by IntelliJ IDEA.
  User: himea
  Date: 2021/5/24
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cn">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <link rel="shortcut icon" href="../img/icon.svg" type="image/x-icon">

    <script src="${pageContext.request.contextPath}/js/jquery-3.6.0.js"></script>
    <script src="${pageContext.request.contextPath}/js/marked.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/atom-one-dark.min.css">
    <script src="${pageContext.request.contextPath}/js/highlight.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/shard.min.css">
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/shards.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <script src="${pageContext.request.contextPath}/js/login.js"></script>
</head>

<body>
<%
    Cookie[] cookies = request.getCookies();
    String username = "";
    String password = "";
    for (Cookie cookie : cookies) {
        if ("username".equals(cookie.getName())) {
            username = cookie.getValue();
        }
        if ("password".equals(cookie.getName())) {
            password = cookie.getValue();
        }
    }
%>
<div class="navbar navbar-main">
    <div class="container">
        <div class="navbar-brand">
            <a href="${pageContext.request.contextPath}/" class="navbar-item navbar-logo">小型文章发布系统</a>
        </div>
        <div class="navbar-item">
            登录页面
        </div>
    </div>
</div>
<div class="login">
    <div class="login-container">
        <div class="login-start"></div>
        <div class="login-end">
            <img src="${pageContext.request.contextPath}/img/user.svg" alt="">
            <form class="form-inline" action="javascript:void(0);">
                <div class="form-group row">
                    <label for="username" class="col-sm-2 col-form-label">用户名</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="username" value="<%=username%>">
                        <div id="username-feedback" class="invalid-feedback"></div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password" class="col-sm-2 col-form-label">密码</label>
                    <div class="col-sm-10">
                        <input type="password" id="password" class="form-control" value="<%=password%>">
                        <div id="password-feedback" class="invalid-feedback"></div>
                    </div>
                </div>

                <script>
                    $("#username").val(<%=username%>);
                    $("#password").val(<%=password%>)
                </script>

                <div class="form-group row">
                    <div class="col-sm-10 offset-sm-2 form-check">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="rememberMe">
                            <label class="form-check-label" for="rememberMe">记住用户名和密码</label>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-10">
                        <button type="submit" class="btn btn-primary" id="submit">提交</button>
                        <div id="submit-feedback"></div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>

<%

%>

</html>
