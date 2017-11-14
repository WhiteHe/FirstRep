package com.cdsxt.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.cdsxt.commons.Pager;

public interface BaseDAO<T,ID extends Serializable> {

	/**
	 * 查询全部  不分页
	 * @return
	 */
	List<T> findAll();
	
	//查询指定参数的列表  不分页
	List<T> find(String hql,Map<String, Object> params);
	
	//根据传入的语句查询结果  进行分页
	Pager find4Page(String hql,Map<String, Object> params,int curr,int size);
	
	//查询全部  并分页
	Pager find4Page(int curr,int size);
	
	//得到所有的 数据条数
	long getCount();
	//获取指定条件的数据的条数
	long getCount(String hql,Map<String,Object> params);
	
	//根据id查询一个
	T findOne(ID id);
	
	//保存一个
	void save(T t);
	//更新
	void update(T t);
	//删除
	void delete(T t);
	//根据id删除
	void delete(ID id);
	
	//创建一个查询对象
	Query buildQuery(String hql,Map<String, Object> params);
	//创建一个sql查询对象
	SQLQuery buildSQLQuery(String hql,Map<String, Object> params);
	
	
}
