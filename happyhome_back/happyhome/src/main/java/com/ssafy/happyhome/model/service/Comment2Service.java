package com.ssafy.happyhome.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhome.model.dto.Comment2;


public interface Comment2Service {
	
	List<Comment2> list(String id);

	boolean create(Comment2 commentDto);

	boolean modify(Comment2 commentDto);

	boolean delete(int commentNo);
	
	Comment2 select(int commentNo);

}
