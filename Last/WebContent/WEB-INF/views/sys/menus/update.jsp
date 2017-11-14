<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commons/import-taglib.jspf" %>
<hpf:template name="" active="sys_menu_manager" title="菜单修改页面">
    <div class="panel panel-default">
        <div class="panel-heading">
            <strong>修改角色</strong>
        </div>
        <div class="panel-body">
            <form action="menu/update.do" class="form-horizontal" method="post">
                <div class="form-group">
                    <label class="control-label col-md-2">菜单名</label>
                    <input type="hidden" name="id" value="${menu.id }">
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="name" value="${menu.name }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">唯一键</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="constant" value="${menu.constant }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">链接地址</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="href" value="${menu.href }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">图标</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="icon" value="${menu.icon }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">是否可用</label>
                    <div class="col-md-10">
                        <label class="radio-inline">
                            <input type="radio" value='true' name="enabled" ${menu.enabled?'checked':'' }/> 是
                        </label>
                        <label class="radio-inline">
                            <input type="radio" value='false' name="enabled" ${!menu.enabled?'checked':'' }/> 否
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">是否显示</label>
                    <div class="col-md-10">
                        <label class="radio-inline">
                            <input type="radio" value='true' name="shown" ${menu.shown?'checked':'' }/> 是
                        </label>
                        <label class="radio-inline">
                            <input type="radio" value='false' name="shown" ${!menu.shown?'checked':'' }/> 否
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">所属菜单</label>
                    <div class="col-md-10">
                    <select class="form-control" name="parent.id">
                        <option>=请选择=</option>
                        <c:forEach items="${menus }" var="m">
                             <option value="${m.id }" ${m eq menu.parent?'selected':'' }>${m.name }</option>
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