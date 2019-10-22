<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/22
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://ajax.aspnetcdn.com/ajax/jquery/jquery-2.1.4.min.js"></script>
<html>
<head>
    <title>文件上传页面</title>
</head>
<body>

    <form action="upload.do" method="post" enctype="multipart/form-data">
        文件:<input type="file" name="photo"/><br />
        <button type="submit">提交</button>
    </form>

    <form>

        <!--multiple:表示可以多选-->
        photo:<input type="file" name="photo" id="ajax" multiple>
        <button type="button" id="btn_ajax">ajax上传</button>
    </form>

<script type="text/javascript">
    $(function(){
        $("#btn_ajax").click(function () {
            var formData = new FormData();
            // var p2 = document.getElementById("ajax");
            // formData.append("photo",file);
            // var file = p2.files[0];

            var filelist = $("#ajax")[0].files;

            formData.append("photo",filelist[1])

            $.ajax({
                url:"upload.do",
                type:"POST",
                data:formData,
                //这个参数是使数据不做处理
                processData: false,
                //注意这里一定要设置contentType:false，不然会默认为传的是字符串，这样文件就传不过去了
                contentType:false
            })
        })
    })
</script>
</body>
</html>
