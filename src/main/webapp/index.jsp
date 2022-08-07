<%@ page import="ga.muzzle.utils.MybatisUtils" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="ga.muzzle.mapper.ArticleMapper" %>
<%@ page import="ga.muzzle.pojo.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="ga.muzzle.pojo.User" %>
<%@ page import="ga.muzzle.mapper.UserMapper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>小型文章发布系统</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.svg" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
<%
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    List<Article> articles = articleMapper.getAllArticle();
    List<User> users = userMapper.getUser();
    sqlSession.close();
%>
<div class="navbar navbar-main">
    <div class="container">
        <div class="navbar-brand">
            <a href="${pageContext.request.contextPath}/index.jsp" class="navbar-item navbar-logo">文章体</a>
        </div>
        <div class="navbar-menu">
            <div class="navbar-start">
                <a href="" class="navbar-item">主页</a>
                <a href="${pageContext.request.contextPath}/archives" class="navbar-item">归档</a>
                <a href="${pageContext.request.contextPath}/categories/" class="navbar-item">分类</a>
                <a href="${pageContext.request.contextPath}/tags/" class="navbar-item">标签</a>
                <a href="${pageContext.request.contextPath}/about/" class="navbar-item">关于</a>
            </div>
            <div class="navbar-end">
                <form class="navbar-item search" action="${pageContext.request.contextPath}/search.html">
                    <label>
                        <input type="text" name="keyword">
                    </label>
                    <button type="submit">
                        <img src="${pageContext.request.contextPath}/img/search.svg" alt="">
                    </button>
                </form>
                <%
                    User user = (User) request.getSession().getAttribute("user");
                    if (user != null) {
                        out.print("<a href='userinfo.html' id='status-1' class='navbar-item'><img src='/img/user.svg' alt=''></a>\n" +
                                "<a href='/user/signOut' id='status-2' class='navbar-item'><img src='/img/sign-out.svg' alt=''></a>");
                    } else {
                        out.print("<a href='user/login.html' id='status-1' class='navbar-item'>登录</a>\n" +
                                "<a href='user/reg.html' id='status-2' class='navbar-item'>注册</a>");
                    }
                %>
            </div>
        </div>
    </div>
</div>
<div class="content">
    <div class="content-container container">
        <div class="content-main">
            <%
                for (Article article : articles) {
                    String text;
                    if (article.getText().length() <= 150) {
                        text = article.getText();
                    } else {
                        text = article.getText().substring(0, 200);
                    }
                    out.print("<div class='contents'>" +
                            "   <a href='/content/index.jsp?id=" + article.getId() + "'>" +
                            "       <div id='title'>" + article.getTitle() + "</div>" +
                            "   </a>" +
                            "   <div id='text'>" + text + "...</div>" +
                            "</div>");
                }
            %>
        </div>
        <div class="content-about">
            欢迎<%
            out.print(user == null ? "游客" : user.getName());
        %>访问<br/><br/>
            网站概况：<br/>
            会员总数：<%=users.size()%><br/>
            文章总数：<%=articles.size()%><br/>
        </div>
    </div>
</div>
<div class="footer">
    <div class="container footer-container">
        <div>
            <img src="${pageContext.request.contextPath}/img/icon.svg" alt="" width=""> 文章体
        </div>
        <div style="font-size: 12px;">&copy;2021 Muzzle Powered by JavaWeb</div>
    </div>
</div>
</body>
</html>
