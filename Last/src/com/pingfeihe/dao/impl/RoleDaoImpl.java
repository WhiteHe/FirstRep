package com.pingfeihe.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pingfeihe.base.impl.BaseDaoImpl;
import com.pingfeihe.dao.RoleDao;
import com.pingfeihe.po.Role;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role, Integer> implements RoleDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findByIds(Integer[] roleIds) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", roleIds);
		return this.createQuery("from Role r where r.id in (:ids)", null).setParameterList("ids", roleIds).list();
	}

}
