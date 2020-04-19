<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%

    String forwardurl=request.getParameter("forwardurl");
    if(forwardurl!=null)
        request.setAttribute("forwardurl", forwardurl);
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>会员登录</title>

    <link rel="stylesheet" href="css/box.all.css" type="text/css"></link>
    <link rel="stylesheet" href="css/login.css" type="text/css"></link>
    <link rel="stylesheet" href="css/reset.css" type="text/css"></link>

    <script  type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script  type="text/javascript" src="js/jquery.validate.min.js"></script>
    <script  type="text/javascript" src="js/jquery.validateex.js"></script>
    <script type="text/javascript"  src="js/jquery.metadata.js" ></script>
    <script type="text/javascript"  src="js/messages_cn.js" ></script>

    <script type="text/javascript">

        $(function(){
            $.metadata.setType("attr","validate");
            $("#form1").validate();
            var userType="${usertype}";

            if(userType!=""){

                $("[name=usertype][value="+userType+"]").prop("checked",true);
            }

        });

    </script>


</head>
<body>
<div class="login_header">
    <div class="header_center clearfix">
        <div class="myfl">
            <span>登陆</span>
        </div>
        <div class="myfr">
            <a href="index">返回首页</a>
        </div>
    </div>
</div>
<div class="login_content clearfix">
    <form name="form1" id="form1" method="post" action="${pageContext.request.contextPath}/admin/qtlogin.do">
        <input type="hidden" name="actiontype" value="login" />
        <input type="hidden"  name="forwardurl" value="${forwardurl}"/>
        <input type="hidden"  name="errorurl" value="/e/login.jsp"/>

        <div class="login-box fn-right">
            <div class="title clearfix">
                <span>用户登录</span>

            </div>
            <div class="content">
                <div class="table-row">
                    <input type="text" class="login-input" name="accountname" validate="{required:true,messages:{required:'请输入账户名'}}"  value="${accountname}" placeholder="用户名" id="username"/>

                </div>

                <div class="table-row">
                    <input type="password" class="login-input" placeholder="登录密码" validate="{required:true,messages:{required:'请输入密码'}}"  id="password" name="password" maxlength="16" value="${password }"/>

                </div>

                <div >

                    <div  class="usertype">
                        用户类型:	 <input type="radio" checked="checked" name="usertype" value="0" />读者

                        <input type="radio" name="usertype" value="1" />作家

                    </div>

                </div>

                <input type="submit" value="立即登录" class="login-btn">
                <p class="entry_mode clearfix">
                    <a href="register" class="myfr">
                        <i>快速注册</i>
                    </a>

                    ${errormsg}
                </p>

            </div>


        </div>
    </form>
</div>
<div class="login_bottom">Copright &nbsp;&nbsp;2020XXXX &nbsp;&nbsp;浙ICP17088888号-1 &nbsp;&nbsp;版权所有</div>
</body>
</html>