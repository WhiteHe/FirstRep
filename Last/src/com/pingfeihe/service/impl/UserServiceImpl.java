package com.pingfeihe.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pingfeihe.commons.Pager;
import com.pingfeihe.dao.RoleDao;
import com.pingfeihe.dao.UserDao;
import com.pingfeihe.po.Role;
import com.pingfeihe.po.User;
import com.pingfeihe.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<User> findAll() {
		return this.userDao.findAll();
	}

	@Override
	public List<User> find(String sql, Map<String, Object> params) {
		return this.userDao.find(sql, params);
	}

	@Override
	public User findOne(Integer id) {
		return this.userDao.findOne(id);
	}

	@Override
	public Pager find4Page(int curPage, int rows) {
		return this.userDao.find4Page(curPage, rows);
	}

	@Override
	public Pager find4Page(String hql, Map<String, Object> params, int curPage, int rows) {
		return this.userDao.find4Page(hql, params, curPage, rows);
	}

	@Override
	public Long getCount() {
		return this.userDao.getCount();
	}

	@Override
	public Long getCount(String hql, Map<String, Object> params) {
		return this.userDao.getCount(hql, params);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(User user) {
		this.userDao.save(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(User user) {
		if (user == null) {
			return;
		}
		this.userDao.update(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Integer id) {
		User user = this.findOne(id);
		if (user != null) {
			this.userDao.delete(user);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(User user) {
		this.userDao.delete(user);
	}

	@Override
	public User findByName(String email) {
		return this.userDao.findByName(email);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void allocate(Integer id, Integer[] roleIds) {
		User user = this.userDao.findOne(id);
		if (user == null) {
			return;
		} 
		Set<Role> roles = user.getRoles();
		roles.clear();
		if (roleIds != null && roleIds.length > 0) {
			roles.addAll(this.roleDao.findByIds(roleIds));
		}
	}

}
