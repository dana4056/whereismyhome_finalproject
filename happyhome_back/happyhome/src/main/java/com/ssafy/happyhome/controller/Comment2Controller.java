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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhome.model.dto.Comment2;
import com.ssafy.happyhome.model.service.Comment2Service;


import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class Comment2Controller {

	private static final Logger logger = LoggerFactory.getLogger(Comment2Controller.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	Comment2Service commentService;

	//커뮤니티 특정 게시물 댓글 리스트 조회
	@GetMapping("freeboard/{id}/comments")
	public ResponseEntity<List<Comment2>> listComment(@PathVariable("id") String id) {
		return new ResponseEntity<>(commentService.list(id), HttpStatus.OK);
	}

	//커뮤니티 댓글 등록
	@PostMapping("freeboard/comment")
	public ResponseEntity<?> createComment(@RequestBody Comment2 comment2Dto) {
		if(commentService.create(comment2Dto)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	//커뮤니티 댓글 수정
	@PutMapping("freeboard/comment")
	public ResponseEntity<?> modifyComment(@RequestBody Comment2 comment2Dto) {
		if(commentService.modify(comment2Dto)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	//커뮤니티 댓글 삭제
	@DeleteMapping("freeboard/comment/{commentNO}")
	public ResponseEntity<?> deleteComment(@PathVariable("commentNO") int commentNO) {
		if(commentService.delete(commentNO)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	//특정 댓글 조회
	@GetMapping("freeboard/comment")
	public ResponseEntity<?> getComment(@RequestParam("no") int commentNO) {

		Comment2 comment = commentService.select(commentNO);
		
		if(comment != null) {
			return new ResponseEntity<Comment2>(comment, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

	}
	
}


