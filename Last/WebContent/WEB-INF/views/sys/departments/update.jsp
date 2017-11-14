<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commons/import-taglib.jspf" %>
<hpf:template name="" active="sys_depar_manager" title="部门修改页面">
    <div class="panel panel-default">
        <div class="panel-heading">
            <strong>修改角色</strong>
        </div>
        <div class="panel-body">
            <form action="department/update.do" class="form-horizontal" method="post">
                <div class="form-group">
                    <label class="control-label col-md-2">部门名称</label>
                    <input name="id" type="hidden" value="${department.id }">
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="name" value="${department.name }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">备注</label>
                    <div class="col-md-10">
                        <textarea class="form-control" rows="3" name="description">${department.description }</textarea>
                    </div>
                </div>
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