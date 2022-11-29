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

import com.ssafy.happyhome.model.dto.Notice;
import com.ssafy.happyhome.model.dto.User;
import com.ssafy.happyhome.model.service.NoticeService;
import com.ssafy.happyhome.model.service.UserService;

@CrossOrigin
@RestController
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	NoticeService service;

	// 공지사항 리스트 조회
	@GetMapping("/notices")
	public ResponseEntity<?> getNoticeList(@RequestParam("page") int currentPage) {

		try {
			int numPerPage = 10;

			logger.info("sdf:{}", currentPage);
			List<Notice> list = service.select((currentPage - 1) * numPerPage, numPerPage);
			int noticeNum = service.getSize();
			int pageCount = getPageCount(10, noticeNum);

			if (list != null && list.size() != 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("list", list);
				map.put("noticeNum", noticeNum);
				map.put("pageCount", pageCount);

				return new ResponseEntity<Map>(map, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 공지사항 게시물 조회
	@GetMapping("/notice/{no}")
	public ResponseEntity<?> getNotice(@PathVariable("no") int no) {
		try {
			Notice notice = service.getNotice(no);

			// 조회수 올리기
			notice.setView(notice.getView() + 1);
			service.update(notice);

			if (notice != null) {
				System.out.println(notice);
				return new ResponseEntity<Notice>(notice, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 공지사항 등록(제목이랑 내용만 기입)
	@PostMapping("/notice")
	public ResponseEntity<?> createNotice(@RequestBody Notice notice) {

		try {
			int result = service.insert(notice);

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

	// 공지사항 수정
	@PutMapping("/notice/{no}")
	public ResponseEntity<?> updateNotice(@PathVariable("no") int no, @RequestBody Notice notice) {
		System.out.println("수정");
		System.out.println(notice);

		try {
			Notice origin = service.getNotice(no);
			System.out.println("공지사항수정");
			if (origin != null) {
				origin.setTitle(notice.getTitle());
				origin.setContent(notice.getContent());

				int result = service.update(origin);

				if (result != 0) {
					return new ResponseEntity<Notice>(origin, HttpStatus.OK);
				} else {
					return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				}
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 공지사항 삭제
	@DeleteMapping("/notice/{no}")
	public ResponseEntity<?> deleteNotice(@PathVariable("no") int no) {

		try {
			int result = service.deleteNotice(no);

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

	// 전체 페이지 구하는 메소드
	public int getPageCount(int numPerPage, int dataCount) {
		int pageCount = 0;

		pageCount = dataCount / numPerPage;

		if (dataCount % numPerPage != 0)
			pageCount++;

		return pageCount;
	}

	// 에러 처리
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 공지사항 핀찍힌 공지 리스트 조회
	@GetMapping("/pinnotices")
	public ResponseEntity<?> getpinNoticeList() {

		try {
			int numPerPage = 10;

			List<Notice> list = service.selectpin();
			int noticeNum = service.getSize();
			int pageCount = getPageCount(10, noticeNum);

			if (list != null && list.size() != 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("list", list);
				map.put("noticeNum", noticeNum);
				map.put("pageCount", pageCount);

				return new ResponseEntity<Map>(map, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 공지사항 수정
	@PutMapping("/setpin/{no}")
	public ResponseEntity<?> setpin(@PathVariable("no") int no) {
		System.out.println("수정");
		

		try {
			Notice origin = service.getNotice(no);

			if (origin != null) {
				int pinsize = service.getPinSize();
				if(origin.getPin() == 0 && pinsize < 3) {
					origin.setPin(1);
				}
				else if(origin.getPin() == 1) {
					origin.setPin(0);
				}
				else if(pinsize >= 3) {
					System.out.println(pinsize);
					return new ResponseEntity<String>("full", HttpStatus.ACCEPTED);
				}
//				notice.setView(notice.getView() + 1);
//				service.update(notice);								

				int result = service.update(origin);

				if (result != 0) {
					return new ResponseEntity<Notice>(origin, HttpStatus.OK);
				} else {
					return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				}
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	
	// 공지사항 리스트 조회
		@GetMapping("/search")
		public ResponseEntity<?> search(@RequestParam("page") int currentPage, @RequestParam("keyword") String keyword ) {

			try {
				int numPerPage = 10;

				logger.info("sdf:{}", currentPage);
				List<Notice> list = service.search((currentPage - 1) * numPerPage, numPerPage, keyword);
				int noticeNum = service.getSize();
				int pageCount = getPageCount(10, noticeNum);

				if (list != null && list.size() != 0) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("list", list);
					map.put("noticeNum", noticeNum);
					map.put("pageCount", pageCount);

					return new ResponseEntity<Map>(map, HttpStatus.OK);
				} else {
					return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				}

			} catch (Exception e) {
				e.printStackTrace();
				return exceptionHandling(e);
			}
		}
	
	
	

}

/*
 * [v] GET /notices?page= 공지사항 리스트 조회 [v] GET /notice/{no} 공지사항 게시물 조회 [v] POST
 * /notice 공지사항 등록 [v] PUT /notice/{no} 공지사항 수정 [v] DELETE /notice/{no} 공지사항 삭제
 */
