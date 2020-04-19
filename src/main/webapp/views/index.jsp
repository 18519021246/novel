<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<%@ include file="import.jsp" %>--%>
<!DOCTYPE HTML>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>

    <link rel="stylesheet" href="css/index.css" type="text/css"></link>
    <link rel="stylesheet" href="css/box.all.css" type="text/css"></link>
    <link rel="stylesheet" href="css/carousel.css" type="text/css"/>

    <%--<script  type="text/javascript" src="js/jquery-1.12.4.min.js"></script>--%>

    <script type='text/javascript' src='js/carousel.js'></script>

    <script type="text/javascript">
        $(function () {
        });
    </script>

</head>
<body>

<jsp:include page="head.jsp"></jsp:include>

<div class="wrap com-panel clearfix">
    <div class="">
        <%--<%=new FocusgraphicBuilder(request).buildFullScreen()%>--%>
    </div>
</div>

<div class="fn-clear"></div>



<div class="wrap round-block ">


    <%--<%=new IndexcolumnsBuilder(request).buildColumns() %>--%>



</div>
<div class="fn-clear"></div>
    <jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>