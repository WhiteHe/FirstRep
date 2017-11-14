package com.pingfeihe.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.pingfeihe.base.impl.BaseDaoImpl;
import com.pingfeihe.dao.ResourceDao;
import com.pingfeihe.po.Resource;
import com.pingfeihe.po.Role;
@Repository
public class ResourceDaoImpl extends BaseDaoImpl<Resource, Integer> implements ResourceDao{

	@Override
	public void delete(Role role) {
		
		Integer id = role.getId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Query query = this.createQuery("delete from Resource r where r.role.id=:id",params );
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> findByRoleId(Integer[] roleIds) {
		Query query = this.createQuery("from Resource r where r.role.id in(:ids)" , null).setParameterList("ids", roleIds);
		return query.list();
	}

}
