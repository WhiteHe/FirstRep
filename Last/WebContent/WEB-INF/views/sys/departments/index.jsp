<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commons/import-taglib.jspf" %>
<hpf:html>
    <hpf:header title="部门首页">
    </hpf:header>
    <hpf:body>
        <hpf:navbar name=""/>
        <hpf:warp active="sys_department_manager">
            <div class="row">
                <div class="col-md-12">
                    <ol class="breadcrumb">
                        <li><a href="">Home</a></li>
                        <li><a href="location:history.go(-1)">Library</a></li>
                        <li class="active">Data</li>
                    </ol>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-user"></span> 部门  列表
                    <div class="btn-toolbar pull-right" role="toolbar" style="margin-top:-5px">
                    <c:if test="${permissions.contains('department/save') }">
                        <div class="btn-group btn-group-sm" >
                            <a type="button" class="btn btn-primary" data-target="#addDepartment" data-toggle="modal"><span class="glyphicon glyphicon-save"></span> 添加部门</a>
                        </div>
                    </c:if>
                    <c:if test="${permissions.contains('department/delete') }">
                        <div class="btn-group btn-group-sm" >
                            <a type="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span>删除选中</a>
                        </div>
                    </c:if>
                    </div>
                </div>
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th><input type="checkbox" onclick="chooseAll(this.checked)"/></th>
                        <th>ID</th>
                        <th>部门名称</th>
                        <th>描述</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.data }" var="department">
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>${department.id }</td>
                            <td>${department.name }</td>
                            <td>${department.description }</td>
                            <td>
                                <c:if test="${permissions.contains('department/update') }">
                                    <a href="department/updateInView.do?id=${department.id }" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-trash"></span> 修改</a>
                                </c:if>
                                <c:if test="${permissions.contains('department/delete') }">
                                    <a href="department/delete.do?id=${department.id }" class="btn btn-warning btn-xs" ><span class="glyphicon glyphicon-edit"></span> 删除</a>
                                </c:if>
                           </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                  <hpf:page url="department/findAll.do"/>
            </div>
            <!-- 用户添加模块 --userController!add-->
            <form action="department/save.do" class="form-horizontal" method="post">
                <div class="modal fade" id="addDepartment" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">  添加部门</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="control-label col-md-2">  部门名称</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="name"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">  部门描述</label>
                                    <div class="col-md-10">
                                        <textarea rows="3" class="form-control" name="description"></textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer ">
                                <div class="col-md-8">
                                    <button type="reset" class="btn btn-warning" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-remove"></span> 取消</button>
                                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-floppy-save"></span> 保存</button>
                                </div>          
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </hpf:warp>
        <hpf:footer/>
</hpf:body>
</hpf:html>
