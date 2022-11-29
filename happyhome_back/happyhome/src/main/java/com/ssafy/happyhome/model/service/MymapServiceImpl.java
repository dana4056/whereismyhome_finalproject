package com.ssafy.happyhome.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhome.model.dao.MymapDao;
import com.ssafy.happyhome.model.dto.Mymap;

@Service
public class MymapServiceImpl implements MymapService {
	
	@Autowired
	MymapDao mymapDao;
	
	public MymapServiceImpl() {}

	public MymapServiceImpl(MymapDao mymap) {
		mymapDao = mymap;
	}

	@Override
	public int insert(Mymap mymap) throws SQLException {
		return mymapDao.insert(mymap);
	}

	@Override
	public List<Mymap> getList(String id, String dongcode) throws SQLException {
		
		return mymapDao.getList(id, dongcode);
	}

	@Override
	public Mymap getMymap(int mymapnum) throws SQLException {
		return mymapDao.getMymap(mymapnum);
	}

	@Override
	public int deleteMymap(String lati, String longi) throws SQLException {
		return mymapDao.deleteMymap(lati, longi);
	}

	
	@Override
	public int getSize() throws SQLException {
		return mymapDao.getSize();
	}

	@Override
	public int mymapdidgood(String id, String lati, String longi) throws SQLException {
		System.out.println(mymapDao.mymapdidgood(id, lati, longi));
		return mymapDao.mymapdidgood(id, lati, longi);
	}

}
