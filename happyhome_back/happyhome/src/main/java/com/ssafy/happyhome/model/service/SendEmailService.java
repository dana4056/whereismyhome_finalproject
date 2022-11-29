package com.ssafy.happyhome.model.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ssafy.happyhome.model.dao.UserDao;
import com.ssafy.happyhome.model.dto.User;
import com.ssafy.happyhome.model.dto.UserMail;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SendEmailService {
	
	@Autowired
	UserDao dao;  //서비스 레이어에서 DAO 사용
	
	@Autowired
	private JavaMailSender mailSender;
	
	private static final String FROM_ADDRESS = "minjateo@naver.com";
    
	// 회원의 이메일과 입력받은 이메일을 비교하는 함수
	public boolean checkEmail(String userEmail, String id) throws SQLException {
		User user = dao.getUser(id);
		
		if(user.getEmail().equals(userEmail)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	// 비밀번호 이메일 생성 및  비밀번호 변경
	public UserMail createMailAndChangePassword(String userEmail, String id) throws SQLException {
		String str = getTempPassword();
        UserMail dto = new UserMail();
        dto.setAddress(userEmail);
        dto.setTitle(id+"님의 WhereIsMyHome 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. WhereIsMyHome 임시비밀번호 안내 관련 이메일 입니다." + "[" + id + "]" +"님의 임시 비밀번호는 "
        + str + " 입니다.");
        
        User user = dao.getUser(id);	
        
		// salt 생성
		String salt = user.getSalt();
		

		// 비밀번호 암호화
		
		String pw = str + salt;
		pw = encryption(pw, 1000);
		
			
		user.setPassword(pw);
		
		dao.update(user);
        
        return dto;
	}
	
   
	// 임시 비밀번호 생성 함수 
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
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
	
	// 메일을 보내는 함수 
	public void mailSend(UserMail mailDto){
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(mailDto.getAddress());
	        message.setFrom(FROM_ADDRESS);
	        message.setSubject(mailDto.getTitle());
	        message.setText(mailDto.getMessage());
	       
	        mailSender.send(message);
	 }

	// 비밀번호 이메일 전송 끝 

}
