package com.pingfeihe.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityFilter implements Filter {

	@Override
	public void destroy() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		//获取对应权限的字符串
		String path = uri.substring(req.getContextPath().length()+1);
		if(path.startsWith("login")){
			chain.doFilter(req, resp);
		}else{
			Object object = req.getSession().getAttribute("principal");
			if(object !=null){
				List<String> permissions = (List<String>) req.getSession().getAttribute("permission");
				String permission = path.substring(0,path.length()-3);
				for (String p : permissions) {
					if(permission.contains(p)){
						chain.doFilter(req, resp);
						return;
					}
				}
				req.setAttribute("msg", "你没有权限,请联系管理员");
				req.getRequestDispatcher("/WEB-INF/views/commons/authorized.jsp").forward(req, resp);
				return;
			}else{
				req.setAttribute("msg", "请登录后再试");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
			
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
