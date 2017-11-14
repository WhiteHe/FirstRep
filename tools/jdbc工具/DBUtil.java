package com.pingfeihe.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {

	/**
	 * 用户名
	 */
	private static String user;
	/**
	 * 密码
	 */
	private static String password;
	/**
	 * 连接数据库的地址
	 */
	private static String url;
	/**
	 * 用静态块让类加载的时候初始化数据，提高效率
	 */
	static {
		try {
			Properties properties = new Properties();
			properties.load(DBUtil.class.getResourceAsStream("/db.properties"));
			user = properties.getProperty("jdbc_user");
			password = properties.getProperty("jdbc_password");
			url = properties.getProperty("jdbc_url");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection(){
		Connection connection = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		}catch(Exception e){
			System.out.println("连接数据库失败！请检查配置文件");
			e.printStackTrace();
		}
		return connection;
	}

	public static void close(AutoCloseable... closeables) {

		if (closeables != null && closeables.length > 0) {
			for (AutoCloseable closeable : closeables) {
				try {
					closeable.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
