package com.ssafy.happyhome.model.service;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhome.model.dao.HouseDao;
import com.ssafy.happyhome.model.dto.HouseDeal;
import com.ssafy.happyhome.model.dto.HouseDetail;
import com.ssafy.happyhome.model.dto.Interest;


@Service
public class HouseServiceImpl implements HouseService {
	
	@Autowired
	HouseDao housedao;
	
	public HouseServiceImpl() {	}
	
	public HouseServiceImpl(HouseDao housedao) {
		this.housedao = housedao;
	}

	@Override
	public List<HouseDetail> fetchDealAll(String aptCode) throws SQLException {
		return housedao.fetchDealAll(aptCode);
	}
	
//	@Override
//	public List<HouseDeal> fetchDealAll(String dongCode) throws SQLException {
//		return housedao.fetchDealAll(dongCode);
//	}
//
//	@Override
//	public List<HouseDeal> fetchDealAll(String dongCode, int year, int month) throws SQLException {
//		return housedao.fetchDealAll(dongCode, year, month);
//	}

	@Override
	public HouseDetail fetchHouseDetail(long no) throws SQLException {
		return housedao.fetchHouseDetail(no);
	}

	@Override
	public int update(HouseDetail housedetail) throws SQLException {
		return housedao.update(housedetail);
	}
	@Override
	public void insert(Interest interest) throws SQLException {
		housedao.insert(interest);
	}

	@Override
	public List<Interest> select(String userid) throws SQLException {
		return housedao.select(userid);
	}
	
	public int delete(Interest interest) throws SQLException {
		return housedao.delete(interest);
	}

	@Override
	public List<Interest> select_detail(String dongcode) throws SQLException {
		return housedao.select_detail(dongcode);
	}

	@Override
	public List<HouseDetail> fetchHomeAll(String dongCode) throws SQLException {
		return housedao.fetchHomeAll(dongCode);
	}


}
