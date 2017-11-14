<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commons/import-taglib.jspf" %>
<hpf:template name="" active="sys_user_manager" title="用户修改页面">
    <div class="panel panel-default">
        <div class="panel-heading">
            <strong>修改用户</strong>
        </div>
        <div class="panel-body">
            <c:if test="${!((msg eq null) or (msg eq ''))}">
                <div class="alert alert-warning alert-dismissible" role="alert">
                      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      ${msg }
                </div>
            </c:if>
            <form action="user/change.do" class="form-horizontal" method="post">
                <div class="form-group">
                    <input type="hidden" value="${principal.id }" name="id">
                    <label class="control-label col-md-2">邮箱</label>
                    <div class="col-md-6">
                        <p class="form-control-static">${principal.email }</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">原始密码</label>
                    <div class="col-md-6">
                        <input type="password" class="form-control" name="password" placeholder="请输入原始密码"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">修改密码</label>
                    <div class="col-md-6">
                        <input type="password" class="form-control" name="changePassword" placeholder="请输入您要修改的密码"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-6">
                        <button type="submit" class="btn btn-warning"> 提交</button>
                        <button type="button" class="btn btn-primary" onclick="location:history.go(-1)"> 取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</hpf:template>
<hpf:footer/>