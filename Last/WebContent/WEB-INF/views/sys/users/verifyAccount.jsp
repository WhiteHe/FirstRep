<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commons/import-taglib.jspf" %>
<hpf:html>
	<hpf:header title="找回密码">
		<style>
			.bor {border-color: red}
		</style>
	</hpf:header>
	<hpf:body>
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header col-md-2">
		      <a class="navbar-brand pull-right" href="login.jsp">密码中心</a>
		    </div>
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		        <li><a href="#" style="color:blue">重置密码 </a></li>
		      </ul>
		      <ul class="nav navbar-nav navbar-right">
		        <li><a href="#">帮助中心</a></li>
		      </ul>
		    </div>
		  </div>
		</nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<div class="panel panel-default">
					  <div class="panel-heading"><h2 class="text-center">输入账号</h2></div>
					  <div class="panel-body">
					    <form class="form-horizontal col-md-6 col-md-offset-3">
						  <div class="form-group">
						    <label class="control-label">请输入要找回密码的邮箱地址：</label>
						    <div>
						      <input type="text" class="form-control">
						    </div>
						  </div>
						  <div class="form-group">
						    <div>
						      <a href="javascript:void(0)" type="button" class="btn btn-primary btn-block"> 下一步</a>
						    </div>
						  </div>
						</form>
					  </div>
					</div>
				</div>
			</div>
		</div>
		<hpf:footer>
    		<script type="text/javascript">
    			$(function(){
    				var flag;
    				$('[type="button"]').on('click',function(){
    					var input = $('form').find('[type="text"]');
    					var val = $(input).val();
    					$(input).next().html('');
    					var val = $(input).val();
    					var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    					if($.trim(val)){
    						
    						if(reg.test(val)){
    							flag =  true;
    						}else{
    							$(input).addClass('bor').after('<lable style="color:red">邮箱格式不正确</lable>');
    							flag = false;
    						}
    					}else{
    						$(input).addClass('bor').after('<lable style="color:red">请输入邮箱</lable>');
    						flag = false;
    					}
    					
    					if(flag){
    						$(input).removeClass('bor');
    						$.ajax({
    							url:'login/verifyAccount.do',
    							data:{"email":val},
    							dataType:"json",
    							success:function(data){
    								if(data!=null){
    									alert(data.msg);
    									if(data.msg==="已经将密码发送至你的邮箱"){
    										window.location.href="login.jsp";
    									}
    								}
    							}
    						})
    					}
    				})
    				
    				$('[type="text"]').on('focus',function(){
    					$(this).next().html('');
    				})
    				
    			})
    		</script>
        </hpf:footer>
	</hpf:body>
</hpf:html>
