<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commons/import-taglib.jspf" %>
<hpf:html>
    <hpf:header title="权限首页">
    </hpf:header>
    <hpf:body>
        <hpf:navbar name=""/>
        <hpf:warp active="sys_menu_manager">
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
                    <span class="glyphicon glyphicon-user"></span> 菜单列表
                    <div class="btn-toolbar pull-right" role="toolbar" style="margin-top:-5px">
                    <c:if test="${permissions.contains('menu/save') }">
                        <div class="btn-group btn-group-sm" >
                            <a type="button" class="btn btn-primary" data-target="#addMenu" data-toggle="modal"><span class="glyphicon glyphicon-save"></span> 添加菜单</a>
                        </div>
                    </c:if>
                    <c:if test="${permissions.contains('menu/delete') }">
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
                        <th>菜单名</th>
                        <th>唯一键</th>
                        <th>链接地址</th>
                        <th>状态</th>
                        <th>所属菜单</th>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.data }" var="menu">
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>${menu.id }</td>
                            <td>${menu.name }</td>
                            <td>${menu.constant }</td>
                            <td>${menu.href }</td>
                            <td>
                                <label class="label ${menu.enabled?'label-primary':'label-danger' }">${menu.enabled?'已启用':'已禁用' }</label>
                                <label class="label ${menu.shown?'label-primary':'label-danger' }">${menu.shown?'显示':'不显示' }</label>
                            </td>
                            <td>${menu.parent == null ?'没有父菜单':menu.parent.name }</td>
                            <td>${menu.description }</td>
                            <td>
                            <c:if test="${permissions.contains('menu/update') }">
                                <a href="menu/updateInView.do?id=${menu.id }" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-trash"></span> 修改</a>
                            </c:if>
                            <c:if test="${permissions.contains('menu/delete') }">
                                <a href="menu/delete.do?id=${menu.id }" class="btn btn-warning btn-xs" ><span class="glyphicon glyphicon-edit"></span> 删除</a>
                            </c:if>
                           </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <hpf:page url="menu/findAll.do"></hpf:page>
            </div>
            <!-- 用户添加模块 --userController!add-->
            <form action="menu/save.do" class="form-horizontal" method="post">
                <div class="modal fade" id="addMenu" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">添加菜单</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="control-label col-md-2">菜单名</label>
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
                                    <label class="control-label col-md-2">链接地址</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="href"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">图标</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="icon"/>
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
                                    <label class="control-label col-md-2">是否显示</label>
                                    <div class="col-md-10">
                                        <label class="radio-inline">
                                            <input type="radio" value='true' name="shown" checked/> 是
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" value='false' name="shown"/> 否
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-2">所属菜单</label>
                                    <div class="col-md-10">
                                    <select class="form-control" name="parent.id">
                                        <option>=请选择=</option>
                                        <c:forEach items="${pager.data }" var="menu">
                                             <option value="${menu.id }">${menu.name }</option>
                                        </c:forEach>
                                    </select>
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
        <hpf:footer>
            <!-- <script type="text/javascript">
                $(function(){
                	  $('[data-toggle=modal]').on('click',function(){
                		  $.ajax({
                			  url:'menu/findAll',
                			  success:function(data){
                				  console.log(data);
                			  }
                		  })
                	  })
                 })                                                            
			</script> -->
        </hpf:footer>
</hpf:body>
</hpf:html>
