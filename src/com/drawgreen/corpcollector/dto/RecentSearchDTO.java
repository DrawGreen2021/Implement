package com.drawgreen.corpcollector.dto;

import java.sql.Timestamp;

public class RecentSearchDTO {
	private int serial_number;
	private String company_name;
	private String location;
	private String sector;
	private String engCorpType;
	private String korCorpType;
	private Timestamp search_date;
	
	public RecentSearchDTO(int serial_number, String company_name, String location, String sector, String engCorpType,
			String korCorpType, Timestamp search_date) {
		super();
		this.serial_number = serial_number;
		this.company_name = company_name;
		this.location = location;
		this.sector = sector;
		this.engCorpType = engCorpType;
		this.korCorpType = korCorpType;
		this.search_date = search_date;
	}

	public int getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(int serial_number) {
		this.serial_number = serial_number;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getEngCorpType() {
		return engCorpType;
	}

	public void setEngCorpType(String engCorpType) {
		this.engCorpType = engCorpType;
	}

	public String getKorCorpType() {
		return korCorpType;
	}

	public void setKorCorpType(String korCorpType) {
		this.korCorpType = korCorpType;
	}

	public Timestamp getSearch_date() {
		return search_date;
	}

	public void setSearch_date(Timestamp search_date) {
		this.search_date = search_date;
	}
	
}
