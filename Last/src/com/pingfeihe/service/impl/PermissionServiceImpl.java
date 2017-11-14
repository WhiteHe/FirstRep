package com.pingfeihe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pingfeihe.commons.Pager;
import com.pingfeihe.dao.PermissionDao;
import com.pingfeihe.po.Permission;
import com.pingfeihe.service.PermissionService;

@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<Permission> findAll() {
		return this.permissionDao.findAll();
	}

	@Override
	public List<Permission> find(String sql, Map<String, Object> params) {
		return this.permissionDao.find(sql, params);
	}

	@Override
	public Permission findOne(Integer id) {
		return this.permissionDao.findOne(id);
	}

	@Override
	public Pager find4Page(int curPage, int rows) {
		return this.permissionDao.find4Page(curPage, rows);
	}

	@Override
	public Pager find4Page(String hql, Map<String, Object> params,
			int curPage, int rows) {
		return this.permissionDao.find4Page(hql, params, curPage, rows);
	}

	@Override
	public Long getCount() {
		return this.permissionDao.getCount();
	}

	@Override
	public Long getCount(String hql, Map<String, Object> params) {
		return this.permissionDao.getCount(hql, params);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(Permission permission) {
		this.permissionDao.save(permission);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Permission permission) {
		if(permission == null){
			return;
		}
		this.permissionDao.update(permission);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Integer id) {
		Permission permission = this.findOne(id);
		if(permission!=null){
			this.permissionDao.delete(permission);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Permission Permission) {
		this.permissionDao.delete(Permission);
	}

	
}
