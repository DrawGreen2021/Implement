package com.drawgreen.corpcollector.dto;

public class InterCorpDTO implements CorpDTO{
	private int serial_number;
	private String company_name;
	private String location;
	private String sector;
	private String corpType;

	public InterCorpDTO(int serial_number, String company_name, String location, String sector, String corpType) {
		super();
		this.serial_number = serial_number;
		this.company_name = company_name;
		this.location = location;
		this.sector = sector;
		this.corpType = corpType;
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

	public String getCorpType() {
		return corpType;
	}

	public void setCorpType(String corpType) {
		this.corpType = corpType;
	}
	
	
}
