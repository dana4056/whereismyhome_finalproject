package com.ssafy.happyhome.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Freeboard {
	private int id;
	private String type;
	private String title;
	private String writer;
	private String content;
	private int view;  //보통 언더바(red_id)보다  낙타표기법 사용
	private int good;

	
}


//`id` int NOT NULL AUTO_INCREMENT,
//`type` int NOT NULL,
//`title` varchar(40) NOT NULL DEFAULT '0',
//`content` text NOT NULL,
//`view` int NOT NULL DEFAULT '0',
//`good` int NOT NULL DEFAULT '0',