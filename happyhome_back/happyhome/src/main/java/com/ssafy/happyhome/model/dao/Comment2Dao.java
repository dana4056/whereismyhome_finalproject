package com.ssafy.happyhome.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhome.model.dto.Notice;
import com.ssafy.happyhome.model.dto.Comment2;

@Mapper
public interface Comment2Dao {
	
	List<Comment2> list(String isbn);

	int create(Comment2 commentDto);

	int modify(Comment2 commentDto);

	int delete(int commentNo);
	
	Comment2 select(int commentNo);
}
