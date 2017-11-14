package com.pingfeihe.dao;

import java.util.List;

import com.pingfeihe.base.BaseDao;
import com.pingfeihe.po.Role;

public interface RoleDao extends BaseDao<Role, Integer> {

	List<Role> findByIds(Integer[] roleIds);

}
