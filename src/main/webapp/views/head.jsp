<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%--<%@ include file="import.jsp" %>--%>
 <script type="text/javascript">
  
    $(function(){

        $(".exit").click(function(){

            var pageurl_pagescope= $("#pageurl_pagescope").val();
            var temusertype=$(this).data("usertype");
            $.ajax({

                url:encodeURI('${pageContext.request.contextPath}/admin/qtlogin.do'),
                method:'post',
                data:{
                    actiontype:'exit',
                    usertype:temusertype
                },
                success:function(){
                    window.location.reload();
                },
                error:function(xmhttprequest,status,excetpion){
                    $.alert("系统错误，错误编码"+status);
                }
            })

        });
      
      $("#searchForm").submit(function(){
    		 var temtitle= $("#title").val();
    		  if(temtitle==""){
    			  
    			  alert("请输入搜索信息");
    			  return false;
    		  }
    		  
    		  
    	  });
      
      $(".main-nav .menus li a").removeClass("current");
	      var headid='<%=request.getParameter("headid")%>';
	      if (headid != '') {
	           
	     	 $("#"+headid).addClass("current");
	 	 }
    
      
      
    })

</script>
 
<%
     
      String title=request.getParameter("title");
      if(title!=null)
    	  request.setAttribute("title", title);
      
     
   
     

 %>
<div class="tab-header">

  <div class="wrap">
			<div class="pull-left">
				<div class="pull-left">
				 <c:if test="${sessionScope.huiyuan!=null}">
					嗨，欢迎<a href="${pageContext.request.contextPath }/e/huiyuan/accountinfo.jsp">${sessionScope.huiyuan.accountname}-${sessionScope.huiyuan.name}</a>
					<span data-usertype="0" class="exit">退出</span>
				</c:if>

					<c:if test="${sessionScope.author!=null}">
						嗨，欢迎<a href="${pageContext.request.contextPath }/e/author/accountinfo.jsp">${sessionScope.author.accountname}-${sessionScope.author.name}</a>
						<span data-usertype="1" class="exit">退出</span>
					</c:if>
				</div>
				<%--<a href="#">帮助中心</a>--%>
			</div>
	  <div class="pull-right">
		  		<a href="login"><span class="cr">登录</span></a>
		  		<c:if test="${sessionScope.huiyuan==null }">
			   </c:if>
				<a href="register">注册</a>

				<a href="loginback">系统后台</a>
			</div>
		</div>

</div>


    <div class="row-flow white-paper">
    <div class="wrap">
        <div style="font-size: 28px; color:#00ba8b; font-weight: bold; width:400px; line-height:30px; font-family:tahoma, arial, Microsoft YaHei, Hiragino Sans GB; padding: 15px 10px;" class="fn-left">
			小说阅读网站
        </div>

		<form id="searchForm"  action="${pageContext.request.contextPath}/admin/sitesearch.do" method="post" >
			<input   type="hidden" name="actiontype" value="find" />
			<input   type="hidden" name="searchtype" value="1" />
			<div class="search-box">
				<div class="search-text">

				</div>
				<div class="keyword">
					<input type="text" placeholder="请输入小说名" id="title" name="title">
				</div>
				<div class="so">
					<input type="submit" class="sobtn" id="btnSearch" value="搜索" name="btnSearch">
				</div>
				<div class="error-container">

				</div>
			</div>

		</form>

    </div>

     
 </div>
  
     
  