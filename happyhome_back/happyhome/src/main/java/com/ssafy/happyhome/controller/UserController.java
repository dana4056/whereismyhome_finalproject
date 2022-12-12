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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.happyhome.model.dto.HouseDeal;
import com.ssafy.happyhome.model.dto.User;
import com.ssafy.happyhome.model.dto.UserMail;
import com.ssafy.happyhome.model.service.SendEmailService;
import com.ssafy.happyhome.model.service.UserService;

@RestController
@CrossOrigin
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	static int KEY_STRETCHING = 1000;

	@Autowired
	UserService service;
	
	@Autowired
	SendEmailService SendEmailService;
	
	
	//로그인 
	@PostMapping("/user/login")
	public ResponseEntity<?> login(@RequestBody User user){
		String id = user.getId();
		String pw = user.getPassword();	
		
		try {
			User loginUser = service.getUser(id);
			String salt = loginUser.getSalt();
			pw = pw + salt;
			pw = encryption(pw, KEY_STRETCHING);
	
			if(loginUser != null && pw.equals(loginUser.getPassword())) {
				logger.info("[UserController.login] 로그인 성공");
				return new ResponseEntity<User>(loginUser, HttpStatus.OK);
			}else { 
				logger.info("[UserController.login] 로그인 실패(그런 아이디 없거나 비번 땡)");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	//회원가입
	@PostMapping("/user")
	public ResponseEntity<?> signup(@RequestBody User user){
		// salt 생성
		String salt = "";
		salt = createsalt(salt);
		user.setSalt(salt);

		// 비밀번호 암호화
		String pw = user.getPassword() + salt;
		pw = encryption(pw, KEY_STRETCHING);
		user.setPassword(pw);

		try {
			int result = service.insert(user);
			
			if(result != 0) {
				logger.info("[UserController.singup] 회원가입 성공");
				return new ResponseEntity<Void>(HttpStatus.OK);
			}else {
				logger.info("[UserController.singup] 회원가입 실패");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	//회원탈퇴
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> delUser(@PathVariable("id") String id){

		try {
			int result = service.deleteUser(id);
			
			if(result != 0) {
				logger.info("[UserController.delUser] 회원탈퇴 성공");
				return new ResponseEntity<Void>(HttpStatus.OK);
			}else {
				logger.info("[UserController.delUser] 회원탈퇴 실패");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	// 회원 정보 수정
	@PutMapping("/user")
	public ResponseEntity<?> updateUser(@RequestBody User user){

		System.out.println(user);
		
		try {
			User origin = service.getUser(user.getId());
			
			if(origin != null) {
				
				// salt 생성
				String salt = "";
				salt = origin.getSalt();

				// 비밀번호 암호화
				String pw = user.getPassword() + salt;
				pw = encryption(pw, KEY_STRETCHING);
				
				origin.setPassword(pw);
				origin.setName(user.getName());
				origin.setAddress(user.getAddress());
				origin.setPhone(user.getPhone());
				origin.setEmail(user.getEmail());

				
				int result = service.update(origin);
				if(result != 0 ) {
					logger.info("[UserController.delUser] 회원수정 성공");
					return new ResponseEntity<User>(origin, HttpStatus.OK);					
				}else {
					logger.info("[UserController.delUser] 회원수정 실패");
					return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				}
			}else {
				logger.info("[UserController.delUser] 회원탈퇴 수정 - 원래 그런 사용자 없음");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	//회원 조회
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") String id){

		try {
			User user = service.getUser(id);
			
			if(user != null) {
				return new ResponseEntity<User>(user, HttpStatus.OK);		
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	//회원 리스트 조회
	@GetMapping("/users")
	public ResponseEntity<?> getUserList(){

		try {
			List<User> users = service.select();
			
			if(users != null && users.size() != 0) {
				return new ResponseEntity<List<User>>(users, HttpStatus.OK);		
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	
	// 암호화 하는 함수
	private String encryption(String pass, int KEY_STRETCHING) {
		String hex = "";
		for (int i = 0; i < KEY_STRETCHING; i++) {
			try {
				MessageDigest msg = MessageDigest.getInstance("SHA-512");
				msg.update(pass.getBytes());
				hex = String.format("%128x", new BigInteger(1, msg.digest()));

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			pass = hex;
		}
		return pass;
	}

	// salt 생성 함수
	private String createsalt(String salt) {
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			byte[] bytes = new byte[16];
			random.nextBytes(bytes);
			salt = new String(Base64.getEncoder().encode(bytes));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return salt;
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error: "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 비밀번호 찾기 API
	// 회원의 이메일과 비교해서 같으면 메일을 보내준다. 
    @PostMapping("/user/find-pw")
    public ResponseEntity<?> sendEmail(@RequestBody HashMap<String, String> map){
    	String userEmail = map.get("userEmail");
    	String id = map.get("id");
    	
    	System.out.println("이메일 비밀번호 실행");
    
    	UserMail dto;
		try {
			if(SendEmailService.checkEmail(userEmail, id)) {
				dto = SendEmailService.createMailAndChangePassword(userEmail, id);
				SendEmailService.mailSend(dto);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
        
    }
}
/*
 [v] POST /user/login    로그인
 [v] POST /user			회원가입
 [v] DELETE /user/{id}	회원탈퇴
 [v] PUT /user/{id}		회원수정
 [v] GET /user/{id}		회원정보 조회
 [v] GET /users			회원리스트 조회
 */

