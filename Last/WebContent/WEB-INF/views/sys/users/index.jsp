<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commons/import-taglib.jspf" %>

<hpf:html>
    <hpf:header title="用户首页">
    </hpf:header>
    <hpf:body>
        <hpf:navbar name=""/>
        <hpf:warp active="sys_user_manager">
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
                    <span class="glyphicon glyphicon-user"></span> 用户列表
                    <div class="btn-toolbar pull-right" role="toolbar" style="margin-top:-5px">
                    <c:if test="${permissions.contains('user/save') }">
                        <div class="btn-group btn-group-sm" >
                            <a type="button" class="btn btn-primary" data-toggle="modal" data-target="#addUser"><span class="glyphicon glyphicon-save"></span> 添加用户</a>
                        </div>
                    </c:if>
                    <c:if test="${permissions.contains('user/delete') }">
                        <div class="btn-group btn-group-sm" >
                            <a type="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span>删除选中</a>
                        </div>
                    </c:if>
                    </div>
                </div>
                <div class="panel-body">
                    <form action="" class="form-inline col-md-12">
                        <div class="form-group">
                            <label class="control-label col-md-4" style="margin-top: 8px">用户名:</label>
                            <div class="input-group col-md-8">
                                <input class="form-control" placeholder="用户名">
                               <span class="input-group-btn">
                                   <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span> 搜索</button>
                               </span>
                            </div>
                        </div>
                    </form>
                </div>
            
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th><input type="checkbox" onclick="chooseAll(this.checked)"/></th>
                        <th>ID</th>
                        <th>邮箱</th>
                        <th>密码</th>
                        <th>真实姓名</th>
                        <th>年龄</th>
                        <th>性别</th>
                        <th>入职日期</th>
                        <th>状态</th>
                        <th>所属部门</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.data }" var="user">
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>${user.id }</td>
                            <td>${user.email }</td>
                            <td>${user.password }</td>
                            <td>${user.realname }</td>
                            <td>${user.age }</td>
                            <td>${user.gender==1?'男':'女' }</td>
                            <td>${user.hiredate }</td>
                            <td>
                                <label class="label ${user.enabled?'label-primary':'label-danger' }">${user.enabled?'已启用':'已禁用' }</label>
                                <label class="label ${user.locked?'label-danger':'label-primary' }">${user.locked?'已锁定':'已解锁' }</label>
                            </td>
                            <td>${user.department.name }</td>
                            <td>
                            <c:if test="${permissions.contains('user/allocate') }">
                                <a href="user/allocateInView.do?id=${user.id }" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-trash"></span> 分配角色</a>
                            </c:if>
                            <c:if test="${permissions.contains('user/delete') }">
                                <a href="user/delete.do?id=${user.id }" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-trash"></span> 删除</a>
                            </c:if>
                            <c:if test="${permissions.contains('user/update') }">
                                <a href="user/updateInView.do?id=${user.id }" class="btn btn-warning btn-xs" ><span class="glyphicon glyphicon-edit"></span> 修改</a>
                            </c:if>
                               </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <hpf:page url="user/findAll.do"/>
            </div>
            <!-- 用户添加模块 --userController!add-->
            <form action="user/save.do" class="form-horizontal" method="post">
                <div class="modal fade" id="addUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">添加用户</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="control-label col-md-2">邮箱</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="email"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">密码</label>
                                    <div class="col-md-10">
                                        <input type="password" class="form-control" name="password"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">真实姓名</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="realname"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">年龄</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="age"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">是否可用</label>
                                    <div class="col-md-10">
                                        <label class="radio-inline">
                                            <input type="radio" value='1' name="gender"/> 男
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" value='0' name="gender"/> 女
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group" style="padding-left: ;padding-right: ;margin-left: 0;margin-right: 0">
                                    <label class="col-md-2 control-label">入职日期</label>
                                    <div class="input-group date form_date col-md-10">
                                        <input class="form-control" type="text" name="hiredate">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">是否可用</label>
                                    <div class="col-md-10">
                                        <label class="radio-inline">
                                            <input type="radio" value='true' name="enabled"/> 是
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" value='false' name="enabled"/> 否
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">是否锁定</label>
                                    <div class="col-md-10">
                                        <label class="radio-inline">
                                            <input type="radio" value="true" name="locked"/> 是
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" value="false" name="locked"/> 否
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">所在部门</label>
                                    <div class="col-md-10">
                                        <select class="form-control" id="dept">
                                            <option>请选择</option>
                                        </select>
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
        <hpf:footer>
            <script type="text/javascript" src="assets/plugins/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
            <script type="text/javascript">
                $('.form_date').datetimepicker({
                    language:  'zh-CN',
                    autoclose: true,
                    todayHighlight: true,
                    format: 'yyyy-mm-dd',
                    minView: 2,
                });
            </script>
        </hpf:footer>
    </hpf:body>
</hpf:html>
