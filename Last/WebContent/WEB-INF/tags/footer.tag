<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" type="java.lang.String" required="false" %>
<!-- 页脚 -->
<div class="footer">
	${title }
</div>
<%@ include file="/WEB-INF/commons/import-js.jspf" %>
<jsp:doBody />              