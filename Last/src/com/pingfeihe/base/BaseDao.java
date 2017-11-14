package com.pingfeihe.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.pingfeihe.commons.Pager;

public interface BaseDao<T, ID extends Serializable> {

	/**
	 * 查询全部字段,并返回一个List
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 根据传入的参数查询，全部的数据,并返回一个List
	 * 
	 * @param sql
	 *            查询数据的sql语句
	 * @param params
	 *            查询数据库对应的参数，也就是sql语句的条件
	 * @return
	 */
	List<T> find(String sql, Map<String,Object> params);

	/**
	 * 根据传入的对象的主键查询对应的数据,并返回一个该类型的对象
	 * 
	 * @param id
	 * @return
	 */
	T findOne(ID id);
	/**
	 * 查询全部,并分页
	 * @param curPage
	 * @param rows
	 * @return
	 */
	Pager find4Page(int curPage, int rows);
	
	/**
	 * 根据传入的参数,构建相应的hql语句,并进行分页
	 * @param hql
	 * @param params
	 * @param curPage
	 * @param rows
	 * @return
	 */
	Pager find4Page(String hql,Map<String,Object> params,int curPage,int rows);
	/**
	 * 查询全部数据的条数
	 * @return
	 */
	Long getCount();
	/**
	 * 根据指定的条件查询具体的条数
	 * @param hql
	 * @param params
	 * @return
	 */
	Long getCount(String hql,Map<String,Object> params);


	/**
	 * 保存传入的类型对象
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 更新传入的类型对象
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 根据传入的id删除对应的对象
	 * @param id
	 */
	void delete(ID id);

	/**
	 * 删除指定的对象
	 * @param entity
	 */
	void delete(T entity);

	/**
	 * 根据传入的sql语句,和对应的参数构建自定义的Query对象
	 * @param sql
	 * @param objects
	 * @return
	 */
	Query createQuery(String sql, Map<String, Object> params);
	Query createSqlQuery(String sql,Map<String, Object> params);
}
