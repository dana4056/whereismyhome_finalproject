package com.ssafy.happyhome.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhome.model.dto.Notice;
import com.ssafy.happyhome.model.dto.Comment;


public interface CommentService {
	
	List<Comment> list(String id);

	boolean create(Comment commentDto);

	boolean modify(Comment commentDto);

	boolean delete(int commentNo);
	
	Comment select(int commentNo);

}
