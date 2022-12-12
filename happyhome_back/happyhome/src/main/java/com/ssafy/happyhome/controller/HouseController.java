package com.ssafy.happyhome.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhome.model.dto.HouseDeal;
import com.ssafy.happyhome.model.dto.HouseDetail;
import com.ssafy.happyhome.model.dto.Interest;
import com.ssafy.happyhome.model.service.HouseService;


@RestController
@CrossOrigin("*")
public class HouseController {

	private static final Logger logger = LoggerFactory.getLogger(HouseController.class);

	@Autowired
	HouseService service;
	
	
	@GetMapping("/homes")
	public ResponseEntity<?> getHomes(@RequestParam("code") String dongCode){
		
		try {
			List<HouseDetail> list = service.fetchHomeAll(dongCode);
			
			 if(list != null && list.size() != 0) {
				 return new ResponseEntity<List<HouseDetail>>(list, HttpStatus.OK);
			 }else {
				 return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	

	
	//아파트별 거래내역 조회
	@GetMapping("/deals")
	public ResponseEntity<?> getDealsByDate(@RequestParam("apt") String aptCode){
		logger.info("getDealsByApt call  apt:{} ",aptCode);
		List<HouseDetail> list = null;
		try {
			
			list = service.fetchDealAll(aptCode);
			System.out.println(list);
			if(list != null && list.size() != 0) {
				System.out.println("hi1");
				return new ResponseEntity<List<HouseDetail>>(list, HttpStatus.OK);
			}else {
				System.out.println("hi2");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
//	//아파트 상세조회
//	@GetMapping("/deal/{no}")
//	public ResponseEntity<?> getDeal(@PathVariable("no") long no){
//		 try {
//			 System.out.println(no);
//			 HouseDetail deal = service.fetchHouseDetail(no);
//			 System.out.println(deal);
//			 
//			 if(deal != null) {
//				 return new ResponseEntity<HouseDeal>(deal, HttpStatus.OK);
//			 }else {
//				 return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			 }
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return exceptionHandling(e);
//		}
//	}
	

//	@PutMapping("/deal/{no}")
//	public ResponseEntity<?> putDeal(@RequestBody HouseDetail houseDetail, @PathVariable("no") long no1){
//		try {
//			HouseDetail deal = service.fetchHouseDetail(no1);
//			
//			logger.info("no: {}", no1);
//			logger.info("before: {}", deal);
//			logger.info("before detail {}", houseDetail);
//			
//			deal.setApartmentName(houseDetail.getApartmentName());
//			deal.setDealAmount(houseDetail.getDealAmount());
//			deal.setDealYear(houseDetail.getDealYear());
//			deal.setDealMonth(houseDetail.getDealMonth());
//			deal.setDealDay(houseDetail.getDealDay());
//			logger.info("ApartmentName - {}", houseDetail.getApartmentName());
//			logger.info("ApartmentName - {}", deal.getApartmentName());
//			logger.info("after: {}", deal);
//			int result = service.update(deal);
//			logger.info("after11: {}", result);
//			
//			if(result != 0) {
//				return new ResponseEntity<HouseDetail>(deal, HttpStatus.OK);
//			}else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return exceptionHandling(e);
//		}
//	}
	
	//관심지역 추가
	@PostMapping("/interest")
	public ResponseEntity<?> postROI(@RequestBody Interest interest){
		try {
			if(interest != null) {
				service.insert(interest);				
				return new ResponseEntity<Void>(HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	
	//관심지역리스트 조회
	@GetMapping("/interests/{id}")
	public ResponseEntity<?> getROIs(@PathVariable("id") String id){

		try {
			List<Interest> list = service.select(id);	
			System.out.println(list);
			if(list != null && list.size() != 0) {
				return new ResponseEntity<List<Interest>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	// 관심지역 삭제
	@DeleteMapping("{id}/interest/{dongCode}") 
	public ResponseEntity<?> deleteROI(@PathVariable("id") String id, @PathVariable("dongCode") String dongCode) {
		try {
			Interest temp = new Interest();
			temp.setId(id);
			temp.setDongCode(dongCode);
			int result = service.delete(temp);
			
			if(result != 0) {
				logger.info("관심지역삭제 성공");
				return new ResponseEntity<Void>(HttpStatus.OK);
			}else {
				logger.info("관심지역삭제 실패");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error: "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
/*
	[v] GET /deals?code=&year=&month 	아파트 거래내역  지역별 모두 조회 & 년월별 조회 (year, month 없으면 동기반 전체 검색)
	[v] GET /deals/interest/{dong_code}  해당 관심지역으로 하우스 딜 리스트 조회
	[v] GET /deal/{no}               	아파트 상세조회
	[v] PUT /deal/{no}             		아파트 정보 수정
	[v] POST /interest                	관심지역 추가
	[v] GET  /interest/{nick}          	관심지역리스트 조회
*/































