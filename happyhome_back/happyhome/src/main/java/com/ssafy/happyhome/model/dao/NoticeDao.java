package com.ssafy.happyhome.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhome.model.dto.Notice;

@Mapper
public interface NoticeDao {
	
	public int insert(Notice notice) throws SQLException;
	public List<Notice> select(int currentPage, int numPerPage) throws SQLException;
	public Notice getNotice(int id) throws SQLException;
	int getSize() throws SQLException;
	
	public int deleteNotice(int id) throws SQLException;
	public int update(Notice notice) throws SQLException;
	
	public List<Notice> selectpin() throws SQLException;
	int getPinSize() throws SQLException;
	
	public List<Notice> search(int currentPage, int numPerPage, String keyword) throws SQLException;

}
