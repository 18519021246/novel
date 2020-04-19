
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>注册</title>
    <link rel="stylesheet" href="css/index.css" type="text/css"></link>
    <link rel="stylesheet" href="css/box.all.css" type="text/css"></link>

    <script  type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script  type="text/javascript" src="js/jquery.validate.min.js"></script>
    <script  type="text/javascript" src="js/jquery.validateex.js"></script>
    <script type="text/javascript"  src="js/jquery.metadata.js" ></script>
    <script type="text/javascript"  src="js/messages_cn.js" ></script>

    <style type="text/css">
        label.error{
            position: absolute;
            top: -2px;
        }
    </style>

    <script type="text/javascript">
        $(function (){
            console.log("register la la la");

            $.metadata.setType("attr","validate");
            $("#form1").validate();
        });
    </script>
</head>
<body>
<div class="full-wrapper">
    <div class="center-wrapper">

        <div class="wrapper-title">
            系统账户注册
        </div>

        <form name="form1" id="form1" method="post" action="${path}/admin/huiyuanmanager.do">
            <input type="hidden" name="actiontype" value="save" />
            <input type="hidden" name="forwardurl" value="/e/regresult.jsp" />
            <div class="wrapper-content">

                <div class="input-pack">
                    <input  type="text" placeholder="账户名" validate="{required:true,messages:{required:'请输入账户名'}}" name="accountname" />
                </div>

                <div class="input-pack">
                    <input  type="password" id="txtPassword" placeholder="密码" validate="{required:true,messages:{required:'请输入密码'}}" name="password" />
                </div>

                <div class="input-pack">
                    <input  type="password" placeholder="确认密码" validate="{required:true,equalTo:'#txtPassword',messages:{required:'请再次输入密码',equalTo:'两次密码不一致'}}" name="password2" />
                </div>


                <div class="input-pack">
                    <input  type="text" placeholder="身份证号" validate="{required:true,idcardno:true,messages:{required:'请输入身份证号',idcardno:'请输入正确的身份证号'}}" name="idcardno" />
                </div>
                <div class="input-pack">
                    <input  type="text" placeholder="姓名" validate="{required:true,messages:{required:'请输入姓名'}}" name="name" />
                </div>

                <div class="input-pack">
                    <input  type="text" placeholder="邮箱" validate="{required:true,email:true,messages:{required:'请输入邮箱',email:'请输入正确邮箱'}}" name="email" />
                </div>
                <div class="agree-protocol">
                    点击注册，表示您同意 <a href="#">《服务协议》</a>

                </div>
                <button class="register-btn">同意并注册</button>

                <div class="agree-protocol">
                    <a href="login">登录系统</a>

                </div>

            </div>
        </form>

    </div>
</div>


</body>
</html>