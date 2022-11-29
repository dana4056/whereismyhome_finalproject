package com.ssafy.happyhome.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhome.model.dto.Notice;
import com.ssafy.happyhome.model.dto.Comment;

@Mapper
public interface CommentDao {
	
	List<Comment> list(String isbn);

	int create(Comment commentDto);

	int modify(Comment commentDto);

	int delete(int commentNo);
	
	Comment select(int commentNo);
}
