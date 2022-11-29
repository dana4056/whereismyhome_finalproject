package com.ssafy.happyhome.model.service;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhome.model.dto.HouseDeal;
import com.ssafy.happyhome.model.dto.HouseDetail;
import com.ssafy.happyhome.model.dto.Interest;



public interface HouseService {
	
	public List<HouseDetail> fetchHomeAll(String dongCode) throws SQLException;   //아파트  지역별 모두 조회
	public List<HouseDetail> fetchDealAll(String aptCode) throws SQLException;   //아파트 거래내역 지역별 모두 조회
//	public List<HouseDeal> fetchDealAll(String dongCode) throws SQLException;   //아파트 거래내역 지역별 모두 조회
//	public List<HouseDeal> fetchDealAll(String dongCode, int year, int month) throws SQLException;   //아파트 거래내역 년월별 조회
	public HouseDetail fetchHouseDetail(long no) throws SQLException; //아파트 상세조회
	public int update(HouseDetail housedetail) throws SQLException;  //아파트 정보 수정
	
	public void insert(Interest interest) throws SQLException; // 관심지역 추가
	public List<Interest> select(String userid) throws SQLException; // 관심지역 조회
	public int delete(Interest interest) throws SQLException; // 관심지역 삭제
	
	public List<Interest> select_detail(String dongcode) throws SQLException; // 관심지역 세부 조회

}
