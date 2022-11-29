package com.ssafy.happyhome.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Comment {
	private int comment_no;
	private String user_name;
	private String comment;
	private String comment_time;
	private String id; //보통 언더바(red_id)보다  낙타표기법 사용
	
	public Comment() {
	}

	public Comment(int comment_no, String user_name, String comment, String comment_time, String id) {
		super();
		this.comment_no = comment_no;
		this.user_name = user_name;
		this.comment = comment;
		this.comment_time = comment_time;
		this.id = id;
	}

	public int getComment_no() {
		return comment_no;
	}

	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment_time() {
		return comment_time;
	}

	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Comment [comment_no=" + comment_no + ", user_name=" + user_name + ", comment=" + comment
				+ ", comment_time=" + comment_time + ", id=" + id + "]";
	}
	
	
	
	
//	 `comment_no` int NOT NULL AUTO_INCREMENT,
//	  `user_name` varchar(16) NOT NULL,
//	  `comment` varchar(500) NOT NULL,
//	  `comment_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
//	  `id` char(12) NOT NULL,
	
}
 