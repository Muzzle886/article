<%@ page import="ga.muzzle.utils.MybatisUtils" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="ga.muzzle.mapper.ArticleMapper" %>
<%@ page import="ga.muzzle.pojo.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="ga.muzzle.pojo.Favorite" %>
<%@ page import="ga.muzzle.pojo.User" %>
<%@ page import="jakarta.servlet.http.HttpServletResponse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="cn">
<head>
    <title>文章内容</title>
    <script src="/js/default.js"></script>
    <link rel="shortcut icon" href="/img/icon.svg" type="image/x-icon">
    <link rel="stylesheet" href="/css/index.css">
    <script src="/js/jquery-3.6.0.js"></script>
    <script src="/js/marked.min.js"></script>
    <link rel="stylesheet" href="/css/atom-one-dark.min.css">
    <script src="/js/highlight.min.js"></script>
</head>
<body>
<%
    String idStr = request.getParameter("id");
    if (idStr==null||"".equals(idStr)){
        out.print("错误！");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        response.sendRedirect("/index.jsp");
        return;
    }
    long id = Long.parseLong(idStr);
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
    Article article = mapper.getArticleById(id);
    if (article==null){
        out.print("文章不存在!");
        sqlSession.close();
        return;
    }
    List<String> tag = mapper.getTag(id);
    List<Favorite> collectionCount = mapper.getCollectionCount(id);
    sqlSession.close();
%>
<div class="navbar navbar-main">
    <div class="container">
        <div class="navbar-brand">
            <a href="/" class="navbar-item navbar-logo">文章体</a>
        </div>
        <div class="navbar-menu">
            <div class="navbar-start">
                <a href="/" class="navbar-item">主页</a>
                <a href="/archives" class="navbar-item">归档</a>
                <a href="/categories/" class="navbar-item">分类</a>
                <a href="/tags/" class="navbar-item">标签</a>
                <a href="/about/" class="navbar-item">关于</a>
            </div>
            <div class="navbar-end">
                <a href="javascript:;" class="navbar-item search">
                    <img src="/img/search.svg" alt="">
                </a>
                <%
                    User user = (User) request.getSession().getAttribute("user");
                    if (user != null) {
                        out.print("<a href='/userinfo.html' id='status-1' class='navbar-item'><img src='/img/user.svg' alt=''></a>\n" +
                                "<a href='/user/signOut' id='status-2' class='navbar-item'><img src='/img/sign-out.svg' alt=''></a>");
                    } else {
                        out.print("<a href='/login.html' id='status-1' class='navbar-item'>登录</a>\n" +
                                "<a href='/reg.html' id='status-2' class='navbar-item'>注册</a>");
                    }
                %>
            </div>
        </div>
    </div>
</div>
<div class="content">
    <div class="container">
        <div class="article">
            <div id="title"><%=article.getTitle()%>
            </div>
            <div class="article-info">
                <img src="/img/user-filling.svg" alt="" title="作者">
                <div id="author"><%=article.getAuthorName()%>
                </div>
                &nbsp;
                <img src="/img/calendar.svg" alt="" title="日期">
                <div id="date"><%=article.getDate()%>
                </div>
                &nbsp;
                <img src="/img/tag.svg" alt="标签" title="标签">
                <div id="tags"><%
                    for (int i = 0; i < tag.size(); i++) {
                        if (i > 0) out.print(",");
                        out.print(tag.get(i));
                    }
                %></div>
            </div>
            <div id="text"><%=article.getText()%>
            </div>
            <script>
                const text = $("#text");
                text.html(marked(text.html()));
                $("pre code").each(function () {
                    hljs.highlightElement(this);
                })
                $('p code').each(function () {
                    $(this).css({"background": "#eee", "border-radius": "3px", "padding": "1px"});
                });
            </script>
            <div id="favorite">
                <img src="/img/favorite.svg" alt="" title="收藏">
                <div id="favorite-count"><%=collectionCount.size()%>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <div class="container footer-container">
        <div>
            <img src="/img/icon.svg" alt="" width=""> 文章体
        </div>
        <div style="font-size: 12px;">&copy;2021 Muzzle Powered by JavaWeb</div>
        <div style="font-size: 14px;">备案号:<a href="https://beian.miit.gov.cn/">湘ICP备2021013149号</a></div>
    </div>
</div>

</body>
</html>
