<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/import.jsp" %>
<!DOCTYPE HTML>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>系统首页</title>
    <link rel="stylesheet" href="${path}/static/css/index.css" type="text/css"></link>
    <%--<link href="${path}/e/css/box.all.css"  rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${path}/e/css/carousel.css" />
    <script type='text/javascript' src='${path}/e/js/carousel.js'></script>--%>

    <script type="text/javascript">
        $(function () {
        });
    </script>

</head>
<body>

<jsp:include page="common/head.jsp"></jsp:include>

<div class="wrap com-panel clearfix">
    <div class="">
        <%=new FocusgraphicBuilder(request).buildFullScreen()%>
    </div>
</div>

<div class="fn-clear"></div>



<div class="wrap round-block ">


    <%=new IndexcolumnsBuilder(request).buildColumns() %>



</div>
<div class="fn-clear"></div>
    <jsp:include page="common/bottom.jsp"></jsp:include>
</body>
</html>