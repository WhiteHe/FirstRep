package com.cdsxt.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.cdsxt.base.BaseDAO;
import com.cdsxt.commons.Pager;
import com.cdsxt.utils.HibUtil;

public abstract class BaseDAOImpl<T,ID extends Serializable> implements BaseDAO<T, ID>{

	//用于获取  class泛型信息
	protected Class<?> clazz;
	public BaseDAOImpl(){
		//得到  超类的  类型
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		//得到真实参数
		this.clazz = (Class<?>) type.getActualTypeArguments()[0];
	}
	
	/**
	 * 查询全部
	 */
	@Override
	public List<T> findAll() {
		String hql = "from "+clazz.getSimpleName();
		return this.find(hql, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		return (List<T>)this.buildQuery(hql, params).list();
	}

	@Override
	public Pager find4Page(String hql, Map<String, Object> params, int curr, int size) {
		//创建一个查询对象
		Query query = this.buildQuery(hql, params);
		//设置查询的最大结果集
		query.setMaxResults(size);
		//设置起始位置
		query.setFirstResult((curr-1)*size);
		//创建分页对象
		Pager pager = new Pager(curr,size);
		pager.setData(query.list());
		pager.setCount(this.getCount("select count(1) "+hql, params));
		return pager;
	}

	@Override
	public Pager find4Page(int curr, int size) {
		String hql = "from "+clazz.getSimpleName();
		return this.find4Page(hql, null,curr,size);
	}

	@Override
	public long getCount() {
		String hql = "select count(1) from "+this.clazz.getSimpleName();
		return getCount(hql,null);
	}

	@Override
	public long getCount(String hql, Map<String, Object> params) {
		Query query = this.buildQuery(hql, params);
		return (Long)query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findOne(ID id) {
		return (T) HibUtil.getSession().get(clazz, id);
	}

	@Override
	public void save(T t) {
		HibUtil.getSession().save(t);
	}

	@Override
	public void update(T t) {
		HibUtil.getSession().update(t);
	}

	@Override
	public void delete(T t) {
		HibUtil.getSession().delete(t);
	}

	@Override
	public void delete(ID id) {
		HibUtil.getSession().delete(this.clazz.getName(),id);
	}

	@Override
	public Query buildQuery(String hql, Map<String, Object> params) {
		
		Session session = HibUtil.getSession();
		Query query = session.createQuery(hql);
		if(params!=null && params.size()>0){
			for(String key :params.keySet()){
				query.setParameter(key, params.get(key));
			}
		}
		return query;
	}

	@Override
	public SQLQuery buildSQLQuery(String hql, Map<String, Object> params) {
		Session session = HibUtil.getSession();
		SQLQuery query = session.createSQLQuery(hql);
		if(params!=null && params.size()>0){
			for(String key :params.keySet()){
				query.setParameter(key, params.get(key));
			}
		}
		return query;
	}
	
}
