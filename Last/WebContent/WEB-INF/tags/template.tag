<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commons/import-taglib.jspf"%>
<%@ attribute name="title" type="java.lang.String" required="true" %>
<%@ attribute name="name" type="java.lang.String" required="true" %>
<%@ attribute name="active" type="java.lang.String" required="true" %>
<hpf:html>
    <hpf:header title="${title }"/>
    <hpf:body>
        <hpf:navbar name="${name }"></hpf:navbar>
        <hpf:warp active="${active }">
            <jsp:doBody/>
        </hpf:warp> 
        <hpf:footer/>       
    </hpf:body>
</hpf:html>