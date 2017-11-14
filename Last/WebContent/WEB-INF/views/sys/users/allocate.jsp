<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commons/import-taglib.jspf" %>
<hpf:template name="" active="sys_user_manager" title="用户修改页面">
    <div class="panel panel-info">
        <div class="panel-heading">
            <strong>分配角色</strong>
        </div>
        <div class="panel-body">
            <h3>正在为编号是：${user.id}, 姓名为：${user.realname }的员工分配角色</h3>
            <form action="user/allocate.do" class="form-horizontal" method="post">
                <input type="hidden" value="${user.id }" name="id">
                <c:forEach items="${roles }" var="role">
                    <div class="checkbox">
                        <label>
                             <input type="checkbox" name="roleIds"  ${user.roles.contains(role)?'checked':'' } value="${role.id }">${ role.name}
                        </label>
                    </div>
                </c:forEach>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-warning"> 提交</button>
                        <button type="button" class="btn btn-primary" onclick="location:history.go(-1)"> 取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</hpf:template>