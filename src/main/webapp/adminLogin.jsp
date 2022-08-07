<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登陆</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/user/admin/login" method="post">
    <input type="text" name="username">
    <input type="password" name="password">
    <button type="submit">提交</button>
</form>

</body>
</html>
