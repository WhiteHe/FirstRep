package com.pingfeihe.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.pingfeihe.base.BaseDao;
import com.pingfeihe.commons.Pager;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {

	protected Class<?> clazz;

	@Autowired
	private SessionFactory sessionFactory;

	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = type.getActualTypeArguments();
		/*if (types == null || types.length != 1) {
			throw new IllegalArgumentException("传入的参数不正确" + types);
		}*/
		this.clazz = (Class<?>) types[0];
	}

	/**
	 * 获取当前线程的Session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public List<T> findAll() {
		return this.getSession().createCriteria(this.clazz).list();
	}

	@Override
	public List<T> find(String sql, Map<String, Object> params) {
		return this.createQuery(sql, params).list();
	}

	@Override
	public T findOne(ID id) {
		return (T) this.getSession().createCriteria(this.clazz).add(Restrictions.idEq(id)).uniqueResult();
	}

	@Override
	public Pager find4Page(int curPage, int rows) {
		return this.find4Page("from "+ this.clazz.getSimpleName(), null, curPage, rows);
	}

	@Override
	public Pager find4Page(String hql, Map<String, Object> params,
			int curPage, int rows) {
		 Query query = this.createQuery(hql, params);
		 query.setFirstResult((curPage-1)*rows);
		 query.setMaxResults(rows);
		 Pager pager = new Pager(curPage, rows);
		 pager.setCount(this.getCount("select count(1) "+hql, params));
		 pager.setData(query.list());
		 return pager;
	}

	@Override
	public Long getCount() {
		String hql = "select count(1) from "+this.clazz.getSimpleName()+" t";
		return this.getCount(hql, null);
	}

	@Override
	public Long getCount(String hql, Map<String, Object> params) {
		return (Long) this.createQuery(hql, params).uniqueResult();
	}

	@Override
	public void save(T entity) {
		this.getSession().save(entity);
	}

	@Override
	public void update(T entity) {
		this.getSession().update(entity);
	}

	@Override
	public void delete(ID id) {
		this.getSession().delete(this.findOne(id));
	}

	@Override
	public void delete(T entity) {
		this.getSession().delete(entity);
	}

	@Override
	public Query createQuery(String sql, Map<String, Object> params) {
		Query query = this.getSession().createQuery(sql);
		if(Objects.nonNull(params)&& !params.isEmpty()){
			Set<Entry<String, Object>> entrySet = params.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
	}

	@Override
	public Query createSqlQuery(String sql, Map<String, Object> params) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		if(Objects.nonNull(params)&& !params.isEmpty()){
			Set<Entry<String, Object>> entrySet = params.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
	}

}
