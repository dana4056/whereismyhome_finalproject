package com.ssafy.happyhome.model.dto;

import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Qna {
	private int id;
	private String title;
	private String writer;
	private String content;
	private int view;  //보통 언더바(red_id)보다  낙타표기법 사용
	private int good;

}


/*
  `id` VARCHAR(50) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `pass` VARCHAR(100) NOT NULL,
  `rec_id` VARCHAR(50) NULL,
 */
 