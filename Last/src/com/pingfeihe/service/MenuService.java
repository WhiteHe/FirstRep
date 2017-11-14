package com.pingfeihe.service;

import java.util.List;
import java.util.Map;

import com.pingfeihe.commons.Pager;
import com.pingfeihe.po.Menu;

public interface MenuService {

	/**
	 * 查询全部字段,并返回一个List
	 * 
	 * @return
	 */
	List<Menu> findAll();

	/**
	 * 根据传入的参数查询，全部的数据,并返回一个List
	 * 
	 * @param sql
	 *            查询数据的sql语句
	 * @param params
	 *            查询数据库对应的参数，也就是sql语句的条件
	 * @return
	 */
	List<Menu> find(String sql, Map<String,Object> params);

	/**
	 * 根据传入的对象的主键查询对应的数据,并返回一个该类型的对象
	 * 
	 * @param id
	 * @return
	 */
	Menu findOne(Integer id);
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
	 * @param Role
	 */
	void save(Menu menu);

	/**
	 * 更新传入的类型对象
	 * @param Role
	 */
	void update(Menu menu);

	/**
	 * 根据传入的id删除对应的对象
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * 删除指定的对象
	 * @param Role
	 */
	void delete(Menu menu);

}
