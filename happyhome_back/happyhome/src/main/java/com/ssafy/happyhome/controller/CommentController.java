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
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	CommentService commentService;

	//특정 qna 게시물의 모든 댓글 조회
	@GetMapping("qna/{id}/comments")
	public ResponseEntity<List<Comment>> listComment(@PathVariable("id") String id) {
		return new ResponseEntity<>(commentService.list(id), HttpStatus.OK);
	}
	
	//댓글 하나 가져오기
	@GetMapping("qna/comment")
	public ResponseEntity<?> getComment(@RequestParam("no") int commentNO) {

		Comment comment = commentService.select(commentNO);
		
		if(comment != null) {
			return new ResponseEntity<Comment>(comment, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

	}
	
	//댓글 등록
	@PostMapping("qna/comment")
	public ResponseEntity<?> createComment(@RequestBody Comment commentDto) {
		if(commentService.create(commentDto)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	//댓글 수정
	@PutMapping("qna/comment")
	public ResponseEntity<?> modifyComment(@RequestBody Comment commentDto) {
		if(commentService.modify(commentDto)) {
			System.out.println("댓글 수정 성공!!!");
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	//댓글 삭제
	@DeleteMapping("qna/comment/{commentNO}")
	public ResponseEntity<?> deleteComment(@PathVariable("commentNO") int commentNO) {
		if(commentService.delete(commentNO)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
}


