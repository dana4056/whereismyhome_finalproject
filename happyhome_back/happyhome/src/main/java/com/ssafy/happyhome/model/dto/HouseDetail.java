package com.ssafy.happyhome.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
public class HouseDetail extends HouseDeal{
	
	int buildYear;  // 건축일
	String floor;	// 층
	String area;	// 면적
	String lat;		// 위도
	String lng;		// 경도
	
	
	
//	public HouseDetail() {
//		super();
//	}
//	public HouseDetail(long no, long aptCode, String apartmentName, String dealAmount, int dealYear, int dealMonth, int dealDay,
//			AreaInfo areainfo, int buildYear, String floor, String area, String lat, String lng) {
//		super.no = no;
//		super.aptCode = aptCode;
//		super.apartmentName = apartmentName;
//		super.dealAmount = dealAmount;
//		super.dealYear = dealYear;
//		super.dealMonth = dealMonth;
//		super.dealDay = dealDay;
//		super.areainfo = areainfo;
//		this.buildYear = buildYear;
//		this.floor = floor;
//		this.area = area;
//		this.lat = lat;
//		this.lng = lng;
//	}
//	
//	
//	public HouseDetail(HouseDeal houseDeal, int buildYear, String floor, String area, String lat, String lng) {
//		super.no = houseDeal.getNo();
//		super.aptCode = houseDeal.getAptCode();
//		super.apartmentName = houseDeal.getApartmentName();
//		super.dealAmount = houseDeal.getDealAmount();
//		super.dealYear = houseDeal.getDealYear();
//		super.dealMonth = houseDeal.getDealMonth();
//		super.dealDay = houseDeal.getDealDay();
//		super.areainfo = houseDeal.getAreainfo();
//		this.buildYear = buildYear;
//		this.floor = floor;
//		this.area = area;
//		this.lat = lat;
//		this.lng = lng;
//	}
//	
//	public HouseDetail(HouseDeal houseDeal) {
//		super.no = houseDeal.getNo();
//		super.aptCode = houseDeal.getAptCode();
//		super.apartmentName = houseDeal.getApartmentName();
//		super.dealAmount = houseDeal.getDealAmount();
//		super.dealYear = houseDeal.getDealYear();
//		super.dealMonth = houseDeal.getDealMonth();
//		super.dealDay = houseDeal.getDealDay();
//		super.areainfo = houseDeal.getAreainfo();
//	}
//	
//	
//
//	public int getBuildYear() {
//		return buildYear;
//	}
//
//	public void setBuildYear(int buildYear) {
//		this.buildYear = buildYear;
//	}
//
//	public String getFloor() {
//		return floor;
//	}
//
//	public void setFloor(String floor) {
//		this.floor = floor;
//	}
//
//	public String getArea() {
//		return area;
//	}
//
//	public void setArea(String area) {
//		this.area = area;
//	}
//
//	public String getLat() {
//		return lat;
//	}
//
//	public void setLat(String lat) {
//		this.lat = lat;
//	}
//
//	public String getLng() {
//		return lng;
//	}
//
//	public void setLng(String lng) {
//		this.lng = lng;
//	}
	@Override
	public String toString() {
		return "HouseDetail [buildYear=" + buildYear + ", floor=" + floor + ", area=" + area + ", lat=" + lat + ", lng="
				+ lng + ", no=" + no + ", aptCode=" + aptCode + ", apartmentName=" + apartmentName + ", dealAmount="
				+ dealAmount + ", dealYear=" + dealYear + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay
				+ ", areainfo=" + areainfo + "]";
	}
//	@Override
//	public String toString() {
//		return "HouseDetail [buildYear=" + buildYear + ", floor=" + floor + ", area=" + area + ", lat=" + lat + ", lng="
//				+ lng + ", no=" + no + ", aptCode=" + aptCode + ", apartmentName=" + apartmentName + ", dealAmount="
//				+ dealAmount + ", dealYear=" + dealYear + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay
//				+ ", areainfo=" + areainfo + "]";
//	}

//	@Override
//	public String toString() {
//		return "Detail [no=" + super.no + ", aptCode=" + super.aptCode + 
//				", apartmentName=" + super.apartmentName + ", dealAmount="+ super.dealAmount + 
//				", dealYear=" + super.dealYear + ", dealMonth=" + super.dealMonth + 
//				", dealDay=" + super.dealDay +", areainfo=" + super.areainfo +
//				", buildYear=" + this.buildYear +", floor=" + this.floor +
//				", area=" + this.area +", lat=" + this.lat +
//				", lng=" + this.lng +"]";
//	}
}

/*
`aptCode` bigint DEFAULT NULL            [housedeal, houseinfo]
`apartmentName` varchar(40) DEFAULT NULL [housedeal]
`dealAmount` varchar(20) DEFAULT NULL,   [housedeal]
`dealYear` int DEFAULT NULL,			 [housedeal]
`dealMonth` int DEFAULT NULL,			 [housedeal]
`dealDay` int DEFAULT NULL,				 [housedeal]
AreaInfo
----------------------------------------------------
`buildYear` int DEFAULT NULL,		     [houseinfo]
`floor` varchar(4) DEFAULT NULL,         [housedeal]
`area` varchar(20) DEFAULT NULL,		 [housedeal]
`lat` varchar(30) DEFAULT NULL, 		 [houseinfo]
`lng` varchar(30) DEFAULT NULL,			 [houseinfo]
*/
		
