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

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
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

import com.ssafy.happyhome.model.dto.Freeboard;
import com.ssafy.happyhome.model.dto.User;
import com.ssafy.happyhome.model.service.FreeboardService;
import com.ssafy.happyhome.model.service.UserService;

@CrossOrigin
@RestController
public class FreeboardController {

	private static final Logger logger = LoggerFactory.getLogger(FreeboardController.class);

	@Autowired
	FreeboardService service;


	
	// 커뮤니티 리스트 조회
	@GetMapping("/freeboards")
	public ResponseEntity<?> getFreeboardList(@RequestParam("page") int currentPage, @RequestParam("type") int type,  @RequestParam("area") String area) {
		
		try {
			int numPerPage = 10;
			
			List<Freeboard> list = null;
			
			list = service.selectByArea((currentPage - 1) * numPerPage, numPerPage, type, area);	

			int freeboardNum = service.getSize();
			int pageCount = getPageCount(10, freeboardNum);
			
			if (list != null && list.size() != 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("list", list);
				map.put("freeboardNum", freeboardNum);
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

	// 커뮤니티 리스트 조회
	@GetMapping("/freeboardsbygood")
	public ResponseEntity<?> getFreeboardListbygood(@RequestParam("page") int currentPage, @RequestParam("type") int type, @RequestParam("area") String area) {

		try {
			int numPerPage = 10;

			List<Freeboard> list = service.selectbygood((currentPage - 1) * numPerPage, numPerPage, type, area);
			int freeboardNum = service.getSize();
			int pageCount = getPageCount(10, freeboardNum);

			if (list != null && list.size() != 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("list", list);
				map.put("freeboardNum", freeboardNum);
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

	// 커뮤니티 리스트 조회
	@GetMapping("/freeboardsbyview")
	public ResponseEntity<?> getFreeboardListbyview(@RequestParam("page") int currentPage, @RequestParam("type") int type, @RequestParam("area") String area) {

		try {
			int numPerPage = 10;

			List<Freeboard> list = service.selectbyview((currentPage - 1) * numPerPage, numPerPage, type, area);
			int freeboardNum = service.getSize();
			int pageCount = getPageCount(10, freeboardNum);

			if (list != null && list.size() != 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("list", list);
				map.put("freeboardNum", freeboardNum);
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

	// 커뮤니티 게시물 조회
	@GetMapping("/freeboard/{no}")
	public ResponseEntity<?> getFreeboard(@PathVariable("no") int no) {

		try {
			Freeboard freeboard = service.getFreeboard(no);

			// 조회수 올리기
			freeboard.setView(freeboard.getView() + 1);
			service.update(freeboard);

			if (freeboard != null) {
				return new ResponseEntity<Freeboard>(freeboard, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 커뮤니티 등록(제목이랑 내용만 기입)
	@PostMapping("/freeboard")
	public ResponseEntity<?> createFreeboard(@RequestBody Freeboard freeboard) {

		try {
			int result = service.insert(freeboard);

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

	// 커뮤니티 수정
	@PutMapping("/freeboard/{no}")
	public ResponseEntity<?> updateFreeboard(@PathVariable("no") int no, @RequestBody Freeboard freeboard) {
		System.out.println("수정");
		System.out.println(freeboard);

		try {
			Freeboard origin = service.getFreeboard(no);

			if (origin != null) {
				origin.setTitle(freeboard.getTitle());
				origin.setContent(freeboard.getContent());

				int result = service.update(origin);

				if (result != 0) {
					return new ResponseEntity<Freeboard>(origin, HttpStatus.OK);
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

	// 커뮤니티 삭제
	@DeleteMapping("/freeboard/{no}/{id}")
	public ResponseEntity<?> deleteFreeboard(@PathVariable("no") int no) {

		try {
			System.out.println("sdf");
			int result = service.deleteFreeboard(no);
			System.out.println("sd222f");
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

	// 커뮤니티 글 좋아요
	@PutMapping("/freeboardgood/{no}/{id}")
	public ResponseEntity<?> Freeboardgood(@PathVariable("no") int no, @PathVariable("id") String id) {

		try {
			Freeboard origin = service.getFreeboard(no);
			
			if (origin != null) {
				System.out.println("sdf"+service.didgood(id, no));
				if(service.didgood(id, no) == 0) {
					origin.setGood(origin.getGood() + 1);
					service.plusgood(id, no);
				} else {
					origin.setGood(origin.getGood() - 1);
					service.minusgood(id, no);
				}
				
				
				int result = service.update(origin);
				System.out.println(result);
				if (result != 0) {
					return new ResponseEntity<Freeboard>(origin, HttpStatus.OK);
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
	
	//커뮤니티 좋아요 했는지 안했는지
			@GetMapping("/freeboarddidgood/{no}/{id}")
			public ResponseEntity<?> didgood(@PathVariable("no") int no, @PathVariable("id") String id){
				
				try {
					int result = service.didgood(id, no);
					
					if(result == 0 || result == 1) {
						return new ResponseEntity<Integer>(result, HttpStatus.OK);				
					}else {
						return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);								
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					return exceptionHandling(e);
				}
			}
	

	// 에러 처리
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
