<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="active" type="java.lang.String" required="true" %>
<%@ include file="/WEB-INF/commons/import-taglib.jspf"   %>
<div class="container-fluid">
    <div class="row"> 
        <div class="col-md-2">
            <hpf:left active="${active }"></hpf:left>
        </div>
        <div class="col-md-10">
            <jsp:doBody/>
        </div>
    </div>
</div>
