package com.cdsxt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtil {
	static{
		//加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("MYSQL jar包没添加哦~!");
			e.printStackTrace();
		}
	}
	public static void close(Object...objs){
		if(objs!=null&&objs.length>0){
			for(Object obj:objs){
				try {
					if(obj instanceof Connection){
						((Connection)(obj)).close();
					}else if(obj instanceof PreparedStatement){
						((PreparedStatement)(obj)).close();
					}else if(obj instanceof ResultSet){
						((ResultSet)(obj)).close();
					}
				} catch (Exception e) {
					System.out.println("关闭数据库资源出错");
					e.printStackTrace();
				}
			}
		}
	}
	
	//获取连接
	public static Connection getConn(){
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		} catch (Exception e) {
			System.out.println("连接数据库出错哦~");
			e.printStackTrace();
		}
		return null;
	}
	
}
