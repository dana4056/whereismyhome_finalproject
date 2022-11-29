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

import com.ssafy.happyhome.model.dto.Comment;
import com.ssafy.happyhome.model.dto.Notice;
import com.ssafy.happyhome.model.dto.User;
import com.ssafy.happyhome.model.service.CommentService;
import com.ssafy.happyhome.model.service.NoticeService;
import com.ssafy.happyhome.model.service.UserService;

import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/comment")
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	CommentService commentService;

	@ApiOperation(value = "id에 해당하는 도서평 목록을 반환한다.", response = List.class)
	@GetMapping("{id}")
	public ResponseEntity<List<Comment>> listComment(@PathVariable("id") String id) {
		logger.debug("listComment - 호출");
		return new ResponseEntity<>(commentService.list(id), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 도서평을 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> createComment(@RequestBody Comment commentDto) {
		logger.debug("createComment - 호출");
		if(commentService.create(commentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호가 comment_no에 해당하는 도서평을 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyComment(@RequestBody Comment commentDto) {
		logger.debug("modifyComment - 호출");
		logger.debug("" + commentDto);
		System.out.println("댓글 수정하자!!!"  + commentDto);
		if(commentService.modify(commentDto)) {
			System.out.println("댓글 수정 성공!!!");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호가 comment_no에 해당하는 도서평을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{commentNO}")
	public ResponseEntity<String> deleteComment(@PathVariable("commentNO") int commentNO) {
		logger.debug("deleteBook - 호출");
		if(commentService.delete(commentNO)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "글번호가 comment_no에 해당하는 댓글을 가져온다.", response = String.class)
	@GetMapping
	public ResponseEntity<?> getComment(@RequestParam("no") int commentNO) {
		logger.debug("getBook - 호출");

		Comment comment = commentService.select(commentNO);
		
		if(comment != null) {
			return new ResponseEntity<Comment>(comment, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

	}
	
}


