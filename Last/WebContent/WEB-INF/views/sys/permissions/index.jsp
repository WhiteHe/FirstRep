<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commons/import-taglib.jspf" %>
<hpf:html>
    <hpf:header title="权限首页">
    </hpf:header>
    <hpf:body>
        <hpf:navbar name=""/>
        <hpf:warp active="sys_permission_manager">
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
                    <span class="glyphicon glyphicon-user"></span> 权限列表
                    <div class="btn-toolbar pull-right" role="toolbar" style="margin-top:-5px">
                    <c:if test="${permissions.contains('permission/save') }">
                        <div class="btn-group btn-group-sm" >
                            <a type="button" class="btn btn-primary" data-toggle="modal" data-target="#addPermission"><span class="glyphicon glyphicon-save"></span> 添加权限</a>
                        </div>
                    </c:if>
                    <c:if test="${permissions.contains('permission/delete') }">
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
                        <th>权限名</th>
                        <th>唯一键</th>
                        <th>状态</th>
                        <th>所属菜单</th>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.data }" var="permission">
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>${permission.id }</td>
                            <td>${permission.name }</td>
                            <td>${permission.constant }</td>
                            <td>
                                <label class="label ${permission.enabled?'label-primary':'label-danger' }">${permission.enabled?'已启用':'已禁用' }</label>
                            </td>
                            <td>${permission.menu.name }</td>
                            <td>${permission.description }</td>
                            <td>
                            <c:if test="${permissions.contains('permission/update') }">
                                <a href="permission/updateInView.do?id=${permission.id }" class="btn btn-warning btn-xs" ><span class="glyphicon glyphicon-edit"></span> 修改</a>
                            </c:if>
                            <c:if test="${permissions.contains('permission/delete') }">
                                <a href="permission/delete.do?id=${permission.id }" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-trash"></span> 删除</a>
                            </c:if>
                           </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <hpf:page url="permission/findAll.do"></hpf:page>
            </div>
            <!-- 用户添加模块 --userController!add-->
            <form action="permission/save.do" class="form-horizontal" method="post">
                <div class="modal fade" id="addPermission" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">添加权限</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="control-label col-md-2">权限名</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="name"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">唯一键</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="constant"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">是否可用</label>
                                    <div class="col-md-10">
                                        <label class="radio-inline">
                                            <input type="radio" value='true' name="enabled" checked/> 是
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" value='false' name="enabled"/> 否
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">所属菜单</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">备注</label>
                                    <div class="col-md-10">
                                        <textarea class="form-control" rows="3" name="description"></textarea>
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
