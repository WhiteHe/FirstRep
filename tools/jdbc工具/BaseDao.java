package com.cdsxt.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;


public class BaseDao {
	//支持   部分字段或全字段
	public static <T> List<T> queryPos(String sql,Class<T> clazz,Object[] params){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<T> empList=new ArrayList<T>();
		try {
			conn=DBUtil.getConn();
			//获取执行sql语句的对象
			//select * from employee;
			ps=conn.prepareStatement(sql);
			//填坑
			if(params!=null&&params.length>0){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			rs=ps.executeQuery();
			//结果集的字段信息
			ResultSetMetaData rsmd=rs.getMetaData();
			//获取有几个字段
			int count=rsmd.getColumnCount();
			
			while(rs.next()){
				T obj=clazz.newInstance();
				//获取字段有哪些 注入属性
				for(int i=1;i<=count;i++){
					//获取要注入的属性名
					String fieldName=rsmd.getColumnName(i);
					//获取属性对象
					Field field=clazz.getDeclaredField(fieldName);
					//获取要注入的值
					Object value=rs.getObject(fieldName);
					//获取设定器的名字
					String setter=getSetter(fieldName);
					//获取设定器的方法
					Method method=clazz.getMethod(setter, field.getType());
					//调用设定器给对象设置值
					method.invoke(obj, value);
				}
				empList.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return empList;
	}
	
	//通过id查询单个数据
	public static <T> T queryById(String sql,Class<T> clazz,int id){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		T t=null;
		try {
			conn=DBUtil.getConn();
			//获取执行sql语句的对象
			//select * from employee;
			ps=conn.prepareStatement(sql);
			//填坑
			ps.setInt(1, id);
			rs=ps.executeQuery();
			//结果集的字段信息
			ResultSetMetaData rsmd=rs.getMetaData();
			//获取有几个字段
			int count=rsmd.getColumnCount();
			
			while(rs.next()){
				t=clazz.newInstance();
				//获取字段有哪些 注入属性
				for(int i=1;i<=count;i++){
					//获取要注入的属性名
					String fieldName=rsmd.getColumnName(i);
					//获取属性对象
					Field field=clazz.getDeclaredField(fieldName);
					//获取要注入的值
					Object value=rs.getObject(fieldName);
					//获取设定器的名字
					String setter=getSetter(fieldName);
					//获取设定器的方法
					Method method=clazz.getMethod(setter, field.getType());
					//调用设定器给对象设置值
					method.invoke(t, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return t;
	}
	
	//增删改
	public static void changePo(String sql,Object[] params){
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=DBUtil.getConn();
			//获取执行sql语句的对象
			ps=conn.prepareStatement(sql);
			//填坑
			if(params!=null&&params.length>0){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps,conn);
		}
	}
	//查询个数
	//select count(*) from 表名 where  条件
	public static int queryCount(String sql,Object[] params){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConn();
			//获取执行sql语句的对象
			ps=conn.prepareStatement(sql);
			//填坑
			if(params!=null&&params.length>0){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			rs=ps.executeQuery();
			
			while(rs.next()){
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return 0;
	}
	
	public static String getSetter(String fieldName){
		return "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
	}
	
}
