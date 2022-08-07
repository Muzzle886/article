<%@ page import="ga.muzzle.utils.MybatisUtils" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="ga.muzzle.mapper.ArticleMapper" %>
<%@ page import="ga.muzzle.pojo.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
    List<Article> allArticle = mapper.getAllArticle();
    ObjectMapper objectMapper = new ObjectMapper();
%>

<% for (Article article : allArticle) {

}
%>



</body>
</html>
