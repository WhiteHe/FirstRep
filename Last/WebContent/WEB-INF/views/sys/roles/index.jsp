<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commons/import-taglib.jspf" %>
<hpf:html>
    <hpf:header title="角色首页">
        <link rel="stylesheet" href="assets/plugins/ztree/css/metroStyle/metroStyle.css">
    </hpf:header>
    <hpf:body>
        <hpf:navbar name=""/>
        <hpf:warp active="sys_role_manager">
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
                    <span class="glyphicon glyphicon-user"></span> 角色列表
                    <div class="btn-toolbar pull-right" role="toolbar" style="margin-top:-5px">
                    <c:if test="${permissions.contains('role/save') }">
                        <div class="btn-group btn-group-sm" >
                            <a type="button" class="btn btn-primary" data-toggle="modal" data-target="#addRole"><span class="glyphicon glyphicon-save"></span> 添加角色</a>
                        </div>
                    </c:if>
                    <c:if test="${permissions.contains('role/delete') }">
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
                        <th>角色名</th>
                        <th>唯一键</th>
                        <th>状态</th>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.data }" var="role">
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>${role.id }</td>
                            <td>${role.name }</td>
                            <td>${role.constant }</td>
                            <td>
                                <label class="label ${role.enabled?'label-primary':'label-danger' }">${role.enabled?'已启用':'已禁用' }</label>
                            </td>
                            <td>${role.description }</td>
                            <td>
                            <c:if test="${permissions.contains('role/allocate') }">
                                <a href="javascript:void(0);" data-id="${role.id }" data-target="#allocatePermission" class="btn btn-warning btn-xs" ><span class="glyphicon glyphicon-edit"></span> 分配权限</a>
                            </c:if>
                            <c:if test="${permissions.contains('role/update') }">
                                <a href="role/updateInView.do?id=${role.id }" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-trash"></span> 修改</a>
                            </c:if>
                           </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <hpf:page url="role/findAll.do"></hpf:page>
            </div>
            <!-- 用户添加模块 --userController!add-->
            <form action="role/save.do" class="form-horizontal" method="post">
                <div class="modal fade" id="addRole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">添加角色</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="control-label col-md-2">角色名</label>
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
            <form action="role/allocate.do" class="form-horizontal" method="post" onsubmit="return handle(this);">
                <div class="modal fade" id="allocatePermission" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">分配权限</h4>
                            </div>
                            <div class="modal-body">
                                <div>
                                    <ul id="ztreeObj" class="ztree"></ul>
                                </div>
                                <div class="data"></div>
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
            <script type="text/javascript" src="assets/plugins/ztree/js/jquery.ztree.all-3.5.min.js"></script>
            <script type="text/javascript">
                var zTreeObj;
                var setting = {
                		check: {
                	        enable: true,
                	        chkDisabledInherit:true
                	    },
                		data:{
                			simpleData:{
                				enable:true,
                                idKey:"id",
                                pIdKey:"pid",
                                rootPId:0
                            }
                		}
                };
                $(function(){
                    $('[data-id]').on('click',function(){
                        var id = $(this).data('id');
                        var target = $(this).data('target');
                        $(target).data('id',id);
                        $.ajax({
                            url:'role/allocateInView.do',
                            data:{'id':id},
                            success:function(data){
                            	zTreeObj=$.fn.zTree.init($("#ztreeObj"), setting, data);
                                $(target).modal();
                            }
                        })
                        $(target).on('hidden.bs.modal',function(){
                        	$("#ztreeObj").empty();
                        	$(target).find('.data').empty();
                        	$(target).removeData('id');
                        })
                    })
                })
                
                function handle(_form){
                	var nodes = zTreeObj.getNodesByParam("checked",true,null);
                	var id = $("#allocatePermission").data('id');
                	$(_form).find('.data').append('<input type="hidden" name="id" value="'+id+'">');
                	$.each(nodes,function(i,o){
                        var menuId;
                        var permissionId;
                        if(o.type==="permission"){
                        	menuId = o.pid;
                        	permissionId = o.id;
                        }else{
                        	menuId = o.id;
                        }
                        $(_form).find('.data').append('<input type="hidden" name="resources['+i+'].menuId" value="'+menuId+'">');
                        if(permissionId){
                        	$(_form).find('.data').append('<input type="hidden" name="resources['+i+'].permissionId" value="'+permissionId+'">');
                        }
                	})
                	return true;
                }
            </script>
        </hpf:footer>
    </hpf:body>
</hpf:html>
