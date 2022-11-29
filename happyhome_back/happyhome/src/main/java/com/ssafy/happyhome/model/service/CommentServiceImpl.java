package com.ssafy.happyhome.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhome.model.dao.NoticeDao;
import com.ssafy.happyhome.model.dto.Notice;
import com.ssafy.happyhome.model.dao.CommentDao;
import com.ssafy.happyhome.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDao commentMapper;

	@Override
	public List<Comment> list(String id) {
		return commentMapper.list(id);
	}

	@Override
	@Transactional
	public boolean create(Comment commentDto) {
		return commentMapper.create(commentDto) == 1;
	}

	@Override
	@Transactional
	public boolean modify(Comment commentDto) {
		return commentMapper.modify(commentDto) == 1;
	}

	@Override
	@Transactional
	public boolean delete(int commentNo) {
		return commentMapper.delete(commentNo) == 1;
	}
	
	@Override
	public Comment select(int commentNo) {
		return commentMapper.select(commentNo);
	}

}
