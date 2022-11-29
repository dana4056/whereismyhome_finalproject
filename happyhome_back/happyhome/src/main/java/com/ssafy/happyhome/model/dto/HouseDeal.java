package com.ssafy.happyhome.model.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HouseDeal {
	long no;				// 거래 번호
	long aptCode;			// 아파트코드
	String apartmentName;	// 아파트이름
	String dealAmount;		// 거래 금액
	int dealYear;			// 거래날짜 (연)
	int dealMonth;			// 거래날짜 (월)
	int dealDay;			// 거래날짜 (일)
	AreaInfo areainfo;		// 법정동(동코드,시도이름,군구이름,동이름)
	
//	public HouseDeal() {}
//
//	public HouseDeal(long no, long aptCode, String apartmentName, String dealAmount, int dealYear, int dealMonth, int dealDay,
//			AreaInfo areainfo) {
//		super();
//		this.no = no;
//		this.aptCode = aptCode;
//		this.apartmentName = apartmentName;
//		this.dealAmount = dealAmount;
//		this.dealYear = dealYear;
//		this.dealMonth = dealMonth;
//		this.dealDay = dealDay;
//		this.areainfo = areainfo;
//	}
//	
//	public long getNo() {
//		return this.no;
//	}
//
//	public void setNo(long no) {
//		this.no = no;
//	}
//
//
//	public long getAptCode() {
//		return aptCode;
//	}
//
//	public void setAptCode(long aptCode) {
//		this.aptCode = aptCode;
//	}
//
//	public String getApartmentName() {
//		return apartmentName;
//	}
//
//	public void setApartmentName(String apartmentName) {
//		this.apartmentName = apartmentName;
//	}
//
//	public String getDealAmount() {
//		return dealAmount;
//	}
//
//	public void setDealAmount(String dealAmount) {
//		this.dealAmount = dealAmount;
//	}
//
//	public int getDealYear() {
//		return dealYear;
//	}
//
//	public void setDealYear(int dealYear) {
//		this.dealYear = dealYear;
//	}
//
//	public int getDealMonth() {
//		return dealMonth;
//	}
//
//	public void setDealMonth(int dealMonth) {
//		this.dealMonth = dealMonth;
//	}
//
//	public int getDealDay() {
//		return dealDay;
//	}
//
//	public void setDealDay(int dealDay) {
//		this.dealDay = dealDay;
//	}
//
//	public AreaInfo getAreainfo() {
//		return areainfo;
//	}
//
//	public void setAreainfo(AreaInfo areainfo) {
//		this.areainfo = areainfo;
//	}
//	
	
}

/*
`aptCode` bigint DEFAULT NULL            [housedeal, houseinfo]
`apartmentName` varchar(40) DEFAULT NULL [housedeal]
`dealAmount` varchar(20) DEFAULT NULL,   [housedeal]
`dealYear` int DEFAULT NULL,			 [housedeal]
`dealMonth` int DEFAULT NULL,			 [housedeal]
`dealDay` int DEFAULT NULL,				 [housedeal]
AreaInfo
*/