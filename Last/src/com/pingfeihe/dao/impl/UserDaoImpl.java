package com.pingfeihe.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pingfeihe.base.impl.BaseDaoImpl;
import com.pingfeihe.dao.UserDao;
import com.pingfeihe.po.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao{

	@Override
	public User findByName(String email) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("email", email);
		return (User) this.createQuery("from User u where u.email=:email", params).uniqueResult();
	}

	
}
