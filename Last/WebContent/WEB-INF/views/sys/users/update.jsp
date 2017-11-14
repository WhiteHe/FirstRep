<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commons/import-taglib.jspf" %>
<hpf:template name="" active="sys_user_manager" title="用户修改页面">
    <div class="panel panel-default">
        <div class="panel-heading">
            <strong>修改用户</strong>
        </div>
        <div class="panel-body">
            <form action="user/update.do" class="form-horizontal" method="post">
                <div class="form-group">
                    <input type="hidden" value="${user.id }" name="id">
                    <label class="control-label col-md-2">邮箱</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="email" value="${user.email }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">密码</label>
                    <div class="col-md-10">
                        <input type="password" class="form-control" name="password" value="${user.password }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">真实姓名</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="realname" value="${user.realname }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">年龄</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="age" value="${user.age }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">是否可用</label>
                    <div class="col-md-10">
                        <label class="radio-inline">
                            <input type="radio" value='1' name="gender" ${user.gender eq 1 ?'checked':'' }/> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" value='0' name="gender" ${user.gender eq 0 ?'checked':'' }/> 女
                        </label>
                    </div>
                </div>
                <div class="form-group" style="padding-left: ;padding-right: ;margin-left: 0;margin-right: 0">
                    <label class="col-md-2 control-label">入职日期</label>
                    <div class="input-group date form_date col-md-10">
                        <input class="form-control" type="text" name="hiredate" value="${user.hiredate }">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">是否可用</label>
                    <div class="col-md-10">
                        <label class="radio-inline">
                            <input type="radio" value='true' name="enabled" ${user.enabled?'checked':'' } /> 是
                        </label>
                        <label class="radio-inline">
                            <input type="radio" value='false' name="enabled" ${!user.enabled?'checked':'' }/> 否
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">是否锁定</label>
                    <div class="col-md-10">
                        <label class="radio-inline">
                            <input type="radio" value="true" name="locked" ${user.locked?'checked':'' }/> 是
                        </label>
                        <label class="radio-inline">
                            <input type="radio" value="false" name="locked" ${!user.locked?'checked':'' }/> 否
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