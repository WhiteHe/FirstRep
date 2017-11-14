<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" type="java.lang.String" required="true" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${title }</title>
    <base href="<%=basePath%>">
    <%@ include file="/WEB-INF/commons/import-css.jspf"%>
    <jsp:doBody/>
</head>