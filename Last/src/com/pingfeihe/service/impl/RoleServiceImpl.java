package com.pingfeihe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pingfeihe.commons.Pager;
import com.pingfeihe.dao.MenuDao;
import com.pingfeihe.dao.PermissionDao;
import com.pingfeihe.dao.ResourceDao;
import com.pingfeihe.dao.RoleDao;
import com.pingfeihe.po.Menu;
import com.pingfeihe.po.Permission;
import com.pingfeihe.po.Role;
import com.pingfeihe.service.RoleService;
import com.pingfeihe.vo.Resource;
import com.pingfeihe.vo.RoleResource;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<Role> findAll() {
		return this.roleDao.findAll();
	}

	@Override
	public List<Role> find(String sql, Map<String, Object> params) {
		return this.roleDao.find(sql, params);
	}

	@Override
	public Role findOne(Integer id) {
		return this.roleDao.findOne(id);
	}

	@Override
	public Pager find4Page(int curPage, int rows) {
		return this.roleDao.find4Page(curPage, rows);
	}

	@Override
	public Pager find4Page(String hql, Map<String, Object> params,
			int curPage, int rows) {
		return this.roleDao.find4Page(hql, params, curPage, rows);
	}

	@Override
	public Long getCount() {
		return this.roleDao.getCount();
	}

	@Override
	public Long getCount(String hql, Map<String, Object> params) {
		return this.roleDao.getCount(hql, params);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(Role Role) {
		this.roleDao.save(Role);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Role Role) {
		if(Role == null){
			return;
		}
		this.roleDao.update(Role);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Integer id) {
		Role Role = this.findOne(id);
		if(Role!=null){
			this.roleDao.delete(Role);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Role Role) {
		this.roleDao.delete(Role);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void allocate(Integer id, RoleResource resource) {
		if(id==null){
			return;
		}
		Role role = this.roleDao.findOne(id);
		resourceDao.delete(role);
		List<Resource> resources = resource.getResources();
		if(resources!=null && resources.size()>0){
			for (Resource res : resources) {
				com.pingfeihe.po.Resource resourcePo = new com.pingfeihe.po.Resource();
				Integer menuId = res.getMenuId();
				Menu menu = menuDao.findOne(menuId);
				Integer permissionId = res.getPermissionId();
				Permission permission = null;
				if(permissionId!= null){
					permission = permissionDao.findOne(permissionId/1000);
				}
				resourcePo.setRole(role);
				resourcePo.setMenu(menu);
				resourcePo.setPermission(permission);
				resourceDao.save(resourcePo);
			}
		}
	}

	
}
