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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhome.model.dto.Mymap;
import com.ssafy.happyhome.model.dto.User;
import com.ssafy.happyhome.model.service.MymapService;
import com.ssafy.happyhome.model.service.UserService;

@CrossOrigin
@RestController
public class MymapController {

	private static final Logger logger = LoggerFactory.getLogger(MymapController.class);

	@Autowired
	MymapService service;

	// Q&A 리스트 조회
	@GetMapping("/mymaps")
	public ResponseEntity<?> getMymapList(@RequestParam("id") String id, @RequestParam("dongcode") String dongcode) {

		try {
			logger.info("sdf:{}", id);
			List<Mymap> list = service.getList(id, dongcode);
			System.out.println(list);
			if (list != null && list.size() != 0) {

				return new ResponseEntity<List<Mymap>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// mymap 하나만 조회
	@GetMapping("/mymap/{no}")
	public ResponseEntity<?> getMymap(@PathVariable("no") int no) {

		try {
			Mymap mymap = service.getMymap(no);

			if (mymap != null) {
				return new ResponseEntity<Mymap>(mymap, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// mymap 등록(제목이랑 내용만 기입)
	@PostMapping("/mymap")
	public ResponseEntity<?> createMymap(@RequestBody Mymap mymap) {

		try {
			int result = service.insert(mymap);

			if (result != 0) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// mymap 삭제
	@DeleteMapping("/mymap")
	public ResponseEntity<?> deleteMymap(@RequestParam("lati") String lati, @RequestParam("longi") String longi) {

		try {
			int result = service.deleteMymap(lati, longi);

			if (result != 0) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

////////////////////////////////////=============수정함==============//////////////////////////////////////
//마이맵 좋아요 했는지 안했는지
	@GetMapping("/mymapdidgood")
	public ResponseEntity<?> mymapdidgood(@RequestParam("id") String id, @RequestParam("lati") String lati,
			@RequestParam("longi") String longi) {

		try {
			int result = service.mymapdidgood(id, lati, longi);
			System.out.println("Mymap like result:" + result);
			if (result > 0) {
				return new ResponseEntity<Integer>(1, HttpStatus.OK);
			} else {
				return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
///////////////////////////////********************************************//////////////////////////////////

	// 에러 처리
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

/*
 * [v] GET /mymaps?page= 공지사항 리스트 조회 [v] GET /mymap/{no} 공지사항 게시물 조회 [v] POST
 * /mymap 공지사항 등록 [v] PUT /mymap/{no} 공지사항 수정 [v] DELETE /mymap/{no} 공지사항 삭제
 */
