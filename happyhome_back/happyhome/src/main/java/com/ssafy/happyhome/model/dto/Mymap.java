package com.ssafy.happyhome.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Mymap {
	String id;
	String dongcode;
	int mymapnum;
	String category_group_code;
	String category_group_name;
	String lati;
	String longi;
	
	
	String addr;
	String apartmentName;
	long aptCode;
	String maxPrice;
	String minPrice;
	String recentPrice;

	String address_name;
	String phone;
	String place_name;
	String place_url;
	String road_address_name;
	
	

}
