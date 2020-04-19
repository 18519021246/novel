<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String path = pageContext.getRequest().getServletContext().getContextPath();
    pageContext.getRequest().setAttribute("path",path);
%>

<%--<script src="${path}/js/jquery-1.12.4.min.js" type="text/javascript"></script>--%>
<script type="text/javascript">
    var path = "${path}";
</script>
