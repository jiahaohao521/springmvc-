<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/22
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传页面</title>
</head>
<body>

    <form action="upload.do" method="post" enctype="multipart/form-data">
        文件:<input type="file" name="photo"/><br />
        <button type="submit">提交</button>
    </form>
</body>
</html>
