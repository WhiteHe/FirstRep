package com.pingfeihe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pingfeihe.commons.Pager;
import com.pingfeihe.dao.DepartmentDao;
import com.pingfeihe.po.Dept;
import com.pingfeihe.service.DepartmentService;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public List<Dept> findAll() {
		return departmentDao.findAll();
	}

	@Override
	public List<Dept> find(String sql, Map<String, Object> params) {
		return departmentDao.find(sql, params);
	}

	@Override
	public Dept findOne(Integer id) {
		return departmentDao.findOne(id);
	}

	@Override
	public Pager find4Page(int curPage, int rows) {
		return departmentDao.find4Page(curPage, rows);
	}

	@Override
	public Pager find4Page(String hql, Map<String, Object> params, int curPage, int rows) {
		return departmentDao.find4Page(hql, params, curPage, rows);
	}

	@Override
	public Long getCount() {
		return departmentDao.getCount();
	}

	@Override
	public Long getCount(String hql, Map<String, Object> params) {
		return departmentDao.getCount(hql, params);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(Dept department) {
		this.departmentDao.save(department);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Dept department) {
		this.departmentDao.update(department);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Integer id) {
		this.departmentDao.delete(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Dept department) {
		this.departmentDao.delete(department);
	}

}
