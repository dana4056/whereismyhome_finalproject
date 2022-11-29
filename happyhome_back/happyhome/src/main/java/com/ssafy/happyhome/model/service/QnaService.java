package com.ssafy.happyhome.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhome.model.dto.Qna;


public interface QnaService {
	
	
	int insert(Qna qna) throws SQLException;
	List<Qna> select(int currentPage, int numPerPage) throws SQLException;
	List<Qna> selectbygood(int currentPage, int numPerPage) throws SQLException;
	List<Qna> selectbyview(int currentPage, int numPerPage) throws SQLException;
	int getSize() throws SQLException;
	Qna getQna(int id) throws SQLException;
	
	public int deleteQna(int id) throws SQLException;
	public int update(Qna qna) throws SQLException;
	
	int didgood(String id, int qnaid) throws SQLException;
	public void plusgood(String id, int qnaid) throws SQLException;
	public void minusgood(String id, int qnaid) throws SQLException;

}
