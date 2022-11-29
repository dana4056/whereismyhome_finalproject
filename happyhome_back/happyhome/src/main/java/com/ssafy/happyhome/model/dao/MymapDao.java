package com.ssafy.happyhome.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhome.model.dto.Mymap;

@Mapper
public interface MymapDao {
	
	public int insert(Mymap mymap) throws SQLException;
	public List<Mymap> getList(String id, String dongcode) throws SQLException;
	public Mymap getMymap(int mymapnum) throws SQLException;
	int getSize() throws SQLException;	
	public int deleteMymap(String lati, String longi) throws SQLException;
	public int mymapdidgood(String id, String lati, String longi) throws SQLException;

}
