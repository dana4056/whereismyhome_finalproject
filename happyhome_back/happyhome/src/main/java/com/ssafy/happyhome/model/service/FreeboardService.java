package com.ssafy.happyhome.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhome.model.dto.Freeboard;




public interface FreeboardService {
	
	int insert(Freeboard freeboard) throws SQLException;
	List<Freeboard> select(int currentPage, int numPerPage, int type) throws SQLException;
	List<Freeboard> selectbygood(int currentPage, int numPerPage, int type) throws SQLException;
	List<Freeboard> selectbyview(int currentPage, int numPerPage, int type) throws SQLException;
	int getSize() throws SQLException;
	Freeboard getFreeboard(int id) throws SQLException;
	
	public int deleteFreeboard(int id) throws SQLException;
	public int update(Freeboard freeboard) throws SQLException;
	int didgood(String id, int freeboardid) throws SQLException;
	public void plusgood(String id, int freeboardid) throws SQLException;
	public void minusgood(String id, int freeboardid) throws SQLException;
}
