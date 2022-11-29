package com.ssafy.happyhome.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhome.model.dto.User;
import com.ssafy.happyhome.model.dto.UserMail;


public interface UserService {
	
	int insert(User user) throws SQLException;
	List<User> select() throws SQLException;
	User getUser(String id) throws SQLException;
	int deleteUser(String id) throws SQLException;
	int update(User user) throws SQLException;
	
	

	
	

}
