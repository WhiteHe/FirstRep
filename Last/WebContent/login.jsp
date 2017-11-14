<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>用户登录</title>
		<base href="<%=basePath%>">
		<link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <style type="text/css">
            .input-group-addon{top:0}
            body{background-color: #669999}
        </style>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="panel panel-default" style="margin-top: 35%">
						<div class="panel-heading"><h4><strong> 用户登录</strong></h4></div>
						<div class="panel-body">
                        <c:if test="${!((msg eq null) or (msg eq ''))}">
                            <div class="alert alert-warning alert-dismissible" role="alert">
                                  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                  ${msg }
                            </div>
                        </c:if>
							<form class="form-horizontal " action="login/login.do" method="post">
								<div class="form-group">
							        <label class="control-label sr-only">Email</label>
									<div class="col-sm-12">
                                        <div class="input-group">
                                            <span class="input-group-addon glyphicon glyphicon-user"></span>
    										<input type="text" class="form-control" name="email" value="${email }" placeholder="邮箱">
                                        </div>
									</div>
								</div>
								<div class="form-group">
                                    <label class="control-label sr-only">Password</label>
									<div class="col-sm-12">
                                        <div class="input-group">
                                            <span class="input-group-addon glyphicon glyphicon-lock "></span>
    										<input type="password" class="form-control" name="password" value="${password }" placeholder="密码">
                                        </div>
									</div>
								</div>
	   
								<div class="form-group">
									<div class=" col-sm-12">
										<button type="submit" class="btn btn-primary btn-block">登录</button>
                                        <a href="login/findPassword.do" >忘记密码 ? ? ?</a>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
        <script type="text/javascript" src="assets/js/jquery.min.js"></script>
        <script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
	</body>
</html>