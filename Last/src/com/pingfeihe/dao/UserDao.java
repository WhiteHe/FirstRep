package com.pingfeihe.dao;

import com.pingfeihe.base.BaseDao;
import com.pingfeihe.po.User;

public interface UserDao extends BaseDao<User, Integer> {

	User findByName(String email);
}
