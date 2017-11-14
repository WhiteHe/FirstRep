package com.pingfeihe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pingfeihe.commons.Pager;
import com.pingfeihe.dao.ComplaintDao;
import com.pingfeihe.po.Complaint;
import com.pingfeihe.service.ComplaintService;

@Service
@Transactional(readOnly = true)
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired
	private ComplaintDao complaintDao;

	@Override
	public List<Complaint> findAll() {
		return this.complaintDao.findAll();
	}

	@Override
	public List<Complaint> find(String sql, Map<String, Object> params) {
		return this.complaintDao.find(sql, params);
	}

	@Override
	public Complaint findOne(Integer id) {
		return this.complaintDao.findOne(id);
	}

	@Override
	public Pager find4Page(int curPage, int rows) {
		return this.complaintDao.find4Page(curPage, rows);
	}

	@Override
	public Pager find4Page(String hql, Map<String, Object> params, int curPage, int rows) {
		return this.complaintDao.find4Page(hql, params, curPage, rows);
	}

	@Override
	public Long getCount() {
		return this.complaintDao.getCount();
	}

	@Override
	public Long getCount(String hql, Map<String, Object> params) {
		return this.complaintDao.getCount(hql, params);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Complaint complaint) {
		this.complaintDao.save(complaint);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Complaint complaint) {
		if (complaint == null) {
			return;
		}
		this.complaintDao.update(complaint);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Integer id) {
		Complaint complaint = this.findOne(id);
		if (complaint != null) {
			this.complaintDao.delete(complaint);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Complaint complaint) {
		this.complaintDao.delete(complaint);
	}

}
