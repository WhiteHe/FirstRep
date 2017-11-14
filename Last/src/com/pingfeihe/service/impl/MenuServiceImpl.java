package com.pingfeihe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pingfeihe.commons.Pager;
import com.pingfeihe.dao.MenuDao;
import com.pingfeihe.po.Menu;
import com.pingfeihe.service.MenuService;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	public List<Menu> findAll() {
		return this.menuDao.findAll();
	}

	@Override
	public List<Menu> find(String sql, Map<String, Object> params) {
		return this.menuDao.find(sql, params);
	}

	@Override
	public Menu findOne(Integer id) {
		return this.menuDao.findOne(id);
	}

	@Override
	public Pager find4Page(int curPage, int rows) {
		return this.menuDao.find4Page(curPage, rows);
	}

	@Override
	public Pager find4Page(String hql, Map<String, Object> params,
			int curPage, int rows) {
		return this.menuDao.find4Page(hql, params, curPage, rows);
	}

	@Override
	public Long getCount() {
		return this.menuDao.getCount();
	}

	@Override
	public Long getCount(String hql, Map<String, Object> params) {
		return this.menuDao.getCount(hql, params);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(Menu menu) {
		if(menu==null){
			return;
		}
		Integer pid= menu.getParent().getId();
		Menu parent = this.findOne(pid);
		if(parent == null){
			return;
		}
		menu.setParent(parent);
		
		this.menuDao.save(menu);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Menu menu) {
		if(menu == null){
			return;
		}
		this.menuDao.update(menu);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Integer id) {
		Menu menu = this.findOne(id);
		if(menu!=null){
			this.menuDao.delete(menu);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Menu menu) {
		this.menuDao.delete(menu);
	}

	
}
