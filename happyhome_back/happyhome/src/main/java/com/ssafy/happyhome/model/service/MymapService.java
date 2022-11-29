package com.ssafy.happyhome.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhome.model.dto.Mymap;


public interface MymapService {
	
	int insert(Mymap mymap) throws SQLException;
	List<Mymap> getList(String id, String dongcode) throws SQLException;
	int getSize() throws SQLException;
	Mymap getMymap(int mymapnum) throws SQLException;	
	public int deleteMymap(String lati, String longi) throws SQLException;
	public int mymapdidgood(String id, String lati, String longi) throws SQLException;

}
