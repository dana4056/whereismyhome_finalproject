package com.ssafy.happyhome.model.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.ssafy.happyhome.model.dao.UserDao;
import com.ssafy.happyhome.model.dto.User;
import com.ssafy.happyhome.model.dto.UserMail;

import lombok.AllArgsConstructor;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao dao;  //서비스 레이어에서 DAO 사용

	 
	//의존관계 주입 왜 여기서 주입? DAO는 인터페이스 기반인데, 여러 구현체 중에 골라서 주입
	//의존관계 주입(injection)1: 생성자 통해 
	public UserServiceImpl() {}
	
	public UserServiceImpl(UserDao dao) {
		this.dao = dao;
	}

	//의존관계 주입(injection)2: setter 통해 
	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	public UserDao getDao() {
		return dao;
	}

	@Override
	public int insert(User user) throws SQLException {
		return dao.insert(user);
	}

	@Override
	public List<User> select() throws SQLException {
		return dao.select();
	}

	@Override
	public User getUser(String id) throws SQLException {
		return dao.getUser(id);
	}


	@Override
	public int deleteUser(String id) throws SQLException {
		return dao.deleteUser(id);
	}

	@Override
	public int update(User user) throws SQLException {
		return dao.update(user);
	}


	

}
