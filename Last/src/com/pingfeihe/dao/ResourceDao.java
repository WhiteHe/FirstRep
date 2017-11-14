package com.pingfeihe.dao;

import java.util.List;

import com.pingfeihe.base.BaseDao;
import com.pingfeihe.po.Resource;
import com.pingfeihe.po.Role;

public interface ResourceDao extends BaseDao<Resource, Integer> {

	void delete(Role role);
	
	List<Resource> findByRoleId(Integer[] roleIds);
}
