package com.ssafy.happyhome.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhome.model.dto.Freeboard;

@Mapper
public interface FreeboardDao {
	
	public int insert(Freeboard freeboard) throws SQLException;
	public List<Freeboard> selectByArea(int currentPage, int numPerPage, int type, String area) throws SQLException;
	public List<Freeboard> select(int currentPage, int numPerPage, int type) throws SQLException;
	public List<Freeboard> selectbygood(int currentPage, int numPerPage, int type, String area) throws SQLException;
	public List<Freeboard> selectbyview(int currentPage, int numPerPage, int type, String area) throws SQLException;
	public Freeboard getFreeboard(int id) throws SQLException;
	int getSize() throws SQLException;
	int didgood(String id, int freeboardid) throws SQLException;
	public int deleteFreeboard(int id) throws SQLException;
	public int update(Freeboard freeboard) throws SQLException;

	public void plusgood(String id, int freeboardid) throws SQLException;
	public void minusgood(String id, int freeboardid) throws SQLException;
	
}
