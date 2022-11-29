package com.ssafy.happyhome.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhome.model.dto.User;


@Mapper
public interface UserDao {
	int insert(User user) throws SQLException;
	List<User> select() throws SQLException;
	User getUser(String id) throws SQLException;
	int deleteUser(String id) throws SQLException;
	int update(User user) throws SQLException;
}
