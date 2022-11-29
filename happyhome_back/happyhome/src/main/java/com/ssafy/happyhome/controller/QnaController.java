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
import com.ssafy.happyhome.model.dto.Qna;
import com.ssafy.happyhome.model.dto.User;
import com.ssafy.happyhome.model.service.QnaService;
import com.ssafy.happyhome.model.service.UserService;

@CrossOrigin
@RestController
public class QnaController {

	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	
	@Autowired
	QnaService service;
	
	//Q&A 리스트 조회
	@GetMapping("/qnas")
	public ResponseEntity<?> getQnaList(@RequestParam("page") int currentPage){
		
		try {
			int numPerPage = 10;
			
			List<Qna> list = service.select((currentPage-1)*numPerPage, numPerPage);
			int qnaNum = service.getSize();
			int pageCount = getPageCount(10, qnaNum);
			
			if(list != null && list.size() != 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("list", list);
				map.put("qnaNum", qnaNum);
				map.put("pageCount", pageCount);
				
				return new ResponseEntity<Map>(map, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	//Q&A 리스트 인기순 조회
		@GetMapping("/qnasbygood")
		public ResponseEntity<?> getQnaListbygood(@RequestParam("page") int currentPage){
			
			try {
				int numPerPage = 10;
				
				List<Qna> list = service.selectbygood((currentPage-1)*numPerPage, numPerPage);
				int qnaNum = service.getSize();
				int pageCount = getPageCount(10, qnaNum);
				
				if(list != null && list.size() != 0) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("list", list);
					map.put("qnaNum", qnaNum);
					map.put("pageCount", pageCount);
					
					return new ResponseEntity<Map>(map, HttpStatus.OK);
				}
				else {
					return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				return exceptionHandling(e);
			}
		}
	
		//Q&A 리스트 조회순 조회
				@GetMapping("/qnasbyview")
				public ResponseEntity<?> getQnaListbyview(@RequestParam("page") int currentPage){
					
					try {
						int numPerPage = 10;
						
						List<Qna> list = service.selectbyview((currentPage-1)*numPerPage, numPerPage);
						int qnaNum = service.getSize();
						int pageCount = getPageCount(10, qnaNum);
						
						if(list != null && list.size() != 0) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("list", list);
							map.put("qnaNum", qnaNum);
							map.put("pageCount", pageCount);
							
							return new ResponseEntity<Map>(map, HttpStatus.OK);
						}
						else {
							return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						return exceptionHandling(e);
					}
				}
		
	//Q&A 게시물 조회
	@GetMapping("/qna/{no}")
	public ResponseEntity<?> getQna(@PathVariable("no") int no){
		
		try {
			Qna qna = service.getQna(no);
			
			//조회수 올리기
			qna.setView(qna.getView()+1);
			service.update(qna);
			
			if(qna != null) {
				return new ResponseEntity<Qna>(qna, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	//Q&A 등록(제목이랑 내용만 기입)
	@PostMapping("/qna")
	public ResponseEntity<?> createQna (@RequestBody Qna qna){
		
		try {
			int result = service.insert(qna);
			
			if(result != 0) {
				return new ResponseEntity<Void>(HttpStatus.OK);				
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);								
			}

		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	//Q&A 수정
	@PutMapping("/qna/{no}")
	public ResponseEntity<?> updateQna (@PathVariable("no") int no, @RequestBody Qna qna){
		
		try {
			Qna origin = service.getQna(no);
			
			if(origin != null) {
				origin.setTitle(qna.getTitle());
				origin.setContent(qna.getContent());
				
				int result = service.update(origin);
				
				if(result != 0) {
					return new ResponseEntity<Qna>(origin, HttpStatus.OK);									
				}else {
					return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				}
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);								
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	} 
	
	//Q&A 삭제
	@DeleteMapping("/qna/{no}")
	public ResponseEntity<?> deleteQna (@PathVariable("no") int no){
		
		try {
			int result = service.deleteQna(no);
			
			if(result != 0) {
				return new ResponseEntity<Void>(HttpStatus.OK);				
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);								
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	//전체 페이지 구하는 메소드
	public int getPageCount(int numPerPage, int dataCount)
	{
		int pageCount = 0;
		
		pageCount = dataCount /numPerPage; 
		
		if(dataCount % numPerPage != 0 )
			pageCount++;
		
		return pageCount;
	}
	


	// 커뮤니티 글 좋아요
	@PutMapping("/qnagood/{no}/{id}")
	public ResponseEntity<?> Freeboardgood(@PathVariable("no") int no, @PathVariable("id") String id) {
		
		try {
			Qna origin = service.getQna(no);
	
			if (origin != null) {
				
				if(service.didgood(id, no) == 0) {
					origin.setGood(origin.getGood() + 1);
					service.plusgood(id, no);
				} else {
					origin.setGood(origin.getGood() - 1);
					service.minusgood(id, no);
				}
				
				
				int result = service.update(origin);
			
				if (result != 0) {
					return new ResponseEntity<Qna>(origin, HttpStatus.OK);
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
	
	//qna좋아요 했는지 안했는지
    @GetMapping("/qnadidgood/{no}/{id}")
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
	
	
	
	
	//에러 처리
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error: "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

/*
	[v] GET    /qnas?page=      공지사항 리스트 조회
	[v] GET    /qna/{no}	  공지사항 게시물 조회
	[v] POST   /qna       공지사항 등록
	[v] PUT    /qna/{no}  공지사항 수정
	[v] DELETE /qna/{no}  공지사항 삭제
*/
