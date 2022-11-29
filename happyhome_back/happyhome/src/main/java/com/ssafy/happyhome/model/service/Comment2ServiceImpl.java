package com.ssafy.happyhome.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhome.model.dao.Comment2Dao;
import com.ssafy.happyhome.model.dto.Comment2;

@Service
public class Comment2ServiceImpl implements Comment2Service {
	
	@Autowired
	private Comment2Dao commentMapper;

	@Override
	public List<Comment2> list(String id) {
		return commentMapper.list(id);
	}

	@Override
	@Transactional
	public boolean create(Comment2 comment2Dto) {
		return commentMapper.create(comment2Dto) == 1;
	}

	@Override
	@Transactional
	public boolean modify(Comment2 comment2Dto) {
		return commentMapper.modify(comment2Dto) == 1;
	}

	@Override
	@Transactional
	public boolean delete(int commentNo) {
		return commentMapper.delete(commentNo) == 1;
	}
	
	@Override
	public Comment2 select(int commentNo) {
		return commentMapper.select(commentNo);
	}

}
