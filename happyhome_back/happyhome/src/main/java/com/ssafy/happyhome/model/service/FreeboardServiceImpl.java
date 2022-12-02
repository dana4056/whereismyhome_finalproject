package com.ssafy.happyhome.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhome.model.dao.FreeboardDao;
import com.ssafy.happyhome.model.dto.Freeboard;

@Service
public class FreeboardServiceImpl implements FreeboardService {
	
	@Autowired
	FreeboardDao freeboardDao;
	
	public FreeboardServiceImpl() {}

	public FreeboardServiceImpl(FreeboardDao dao) {
		freeboardDao = dao;
	}

	@Override
	public int insert(Freeboard freeboard) throws SQLException {
		return freeboardDao.insert(freeboard);
	}
	
	@Override
	public List<Freeboard> selectByArea(int currentPage, int numPerPage, int type, String area) throws SQLException {
		return freeboardDao.selectByArea(currentPage, numPerPage, type, area);
	}

	@Override
	public List<Freeboard> select(int currentPage, int numPerPage, int type) throws SQLException {
		return freeboardDao.select(currentPage, numPerPage, type);
	}
	
	@Override
	public List<Freeboard> selectbyview(int currentPage, int numPerPage, int type, String area) throws SQLException {
		return freeboardDao.selectbyview(currentPage, numPerPage, type,area);
	}
	
	@Override
	public List<Freeboard> selectbygood(int currentPage, int numPerPage, int type, String area) throws SQLException {
		return freeboardDao.selectbygood(currentPage, numPerPage, type,area);
	}

	@Override
	public Freeboard getFreeboard(int id) throws SQLException {
		return freeboardDao.getFreeboard(id);
	}

	@Override
	public int deleteFreeboard(int id) throws SQLException {
		return freeboardDao.deleteFreeboard(id);
	}

	@Override
	public int update(Freeboard freeboard) throws SQLException {
		return freeboardDao.update(freeboard);
	}

	@Override
	public int getSize() throws SQLException {
		return freeboardDao.getSize();
	}

	@Override
	public int didgood(String id, int freeboardid) throws SQLException {
		System.out.println("123456");
		return freeboardDao.didgood(id, freeboardid);
	}

	@Override
	public void plusgood(String id, int freeboardid) throws SQLException {
		freeboardDao.plusgood(id, freeboardid);
	}

	@Override
	public void minusgood(String id, int freeboardid) throws SQLException {
		freeboardDao.minusgood(id, freeboardid);
	}


}
