package com.ssafy.happyhome.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhome.model.dto.Qna;

@Mapper
public interface QnaDao {
	
	public int insert(Qna qna) throws SQLException;
	public List<Qna> select(int currentPage, int numPerPage) throws SQLException;
	public List<Qna> selectbygood(int currentPage, int numPerPage) throws SQLException;
	public List<Qna> selectbyview(int currentPage, int numPerPage) throws SQLException;
	public Qna getQna(int id) throws SQLException;
	int getSize() throws SQLException;
	
	public int deleteQna(int id) throws SQLException;
	public int update(Qna qna) throws SQLException;
	
	int didgood(String id, int qnaid) throws SQLException;
	public void plusgood(String id, int qnaid) throws SQLException;
	public void minusgood(String id, int qnaid) throws SQLException;

}
