<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commons/import-taglib.jspf" %>
<%@ attribute name="active" type="java.lang.String" required="false" %>
<div class="panel panel-default side-nav">
	<div class="panel-heading">
		<strong><span class="glyphicon glyphicon-list"></span> 菜单</strong>
	</div>
	<ul class="nav side-nav">
        <c:forEach items="${sysmenus }" var="menu">
           <c:if test="${menu.parent ==null }">
                <li class="${active == menu.constant?'active':'' }"><a href="${menu.href }"><span class="${menu.icon }"></span> ${menu.name }</a>
                <c:if test="${fn:length(menu.menus)>0 }">
                    <ul class="nav" style="display: none">
                        <c:forEach items="${menu.menus }" var="m">
                            <c:if test="${sysmenus.contains(m) }">
                                <li class="${active == m.constant?'active':'' }"><a href="${m.href }"><span class="${m.icon }"></span> ${m.name }</a></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </c:if>
                </li>
           </c:if>
        
        </c:forEach>
   </ul>
</div>

<%--         
       <li><a href="#"><span class="glyphicon "></span> 客户管理</a></li>
       <li><a href="#"><span class="glyphicon glyphicon-eur"></span> 财务管理</a></li>
       <li><a href="#"><span class="glyphicon glyphicon-indent-right"></span> 公司管理</a></li>
       <li><a href="#"><span class="glyphicon glyphicon-film"></span> 数据统计</a></li>
       <li>
           <a href="javascript:void(0)"><span class="glyphicon glyphicon-cog"></span> 系统设置 <span class="glyphicon glyphicon-menu-down pull-right"></span></a>
           <ul class="nav" style="display: none">
               <li class="${active == 'sys_department_manager'?'active':'' }"><a href="department/findAll.do"><span class="glyphicon glyphicon-inbox"></span> 部门管理</a></li>
               <li class="${active == 'sys_user_manager'?'active':'' }"><a href="user/findAll.do"><span class="glyphicon glyphicon-user"></span> 用户管理</a></li>
               <li class="${active == 'sys_role_manager'?'active':'' }"><a href="role/findAll.do"><span class="glyphicon glyphicon-heart"></span> 角色管理</a></li>
               <li class="${active == 'sys_menu_manager'?'active':'' }"><a href="menu/findAll.do"><span class="glyphicon glyphicon-list"></span> 菜单管理</a></li>
               <li class="${active == 'sys_permission_manager'?'active':'' }"><a href="permission/findAll.do"><span class="glyphicon glyphicon-lock"></span> 权限管理</a></li>
           </ul>  --%>