package com.ssafy.happyhome.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment2 {
	private int comment_no;
	private String user_name;
	private String comment;
	private String comment_time;
	private String id; //보통 언더바(red_id)보다  낙타표기법 사용
	
	
	
	
	
	
//	 `comment_no` int NOT NULL AUTO_INCREMENT,
//	  `user_name` varchar(16) NOT NULL,
//	  `comment` varchar(500) NOT NULL,
//	  `comment_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
//	  `id` char(12) NOT NULL,
	
}
 