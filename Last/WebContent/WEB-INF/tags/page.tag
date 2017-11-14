<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="url" type="java.lang.String" required="true"%>

<div class="col-md-12 col-md-offset-4" style="padding: 0">
    <nav aria-label="Page navigation">
        <ul class="pagination" style="margin-top: 3px;">
            <c:if test="${pager.pageNum!=pager.first }">
                <li><a href="${url }?current=${pager.first }">首页</a><li>
                <li><a href="${url }?current=${pager.prev }">上一页</a></li>
            </c:if>
            <c:forEach begin="${pager.pageStart }" end="${pager.pageEnd }" var="i">
                <c:choose>
                    <c:when test="${pager.pageNum==i }">
                        <li><span style="color:red">${i }</span><li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${url }?current=${i }">${i }</a><li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${pager.pageNum!=pager.last }">
                <li><a href="${url }?current=${pager.next }">下一页</a><li>
                <li><a href="${url }?current=${pager.last }">尾页</a><li>
            </c:if>
        </ul>
    </nav>
</div>