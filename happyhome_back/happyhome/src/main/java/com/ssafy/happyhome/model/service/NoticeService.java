package com.ssafy.happyhome.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhome.model.dto.Notice;


public interface NoticeService {
	
	int insert(Notice notice) throws SQLException;
	List<Notice> select(int currentPage, int numPerPage) throws SQLException;
	int getSize() throws SQLException;
	Notice getNotice(int id) throws SQLException;
	
	public int deleteNotice(int id) throws SQLException;
	public int update(Notice notice) throws SQLException;
	
	public List<Notice> selectpin() throws SQLException;
	int getPinSize() throws SQLException;
	
	public List<Notice> search(int currentPage, int numPerPage, String keyword) throws SQLException;

}
