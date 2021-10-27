package com.drawgreen.corpcollector.dto;

public class FamilyFriendlyCorpDTO implements CorpDTO{
	private int serial_number;
	private String company_name;
	private String division;
	private String city_state;
	
	public FamilyFriendlyCorpDTO(int serial_number, String company_name, String division, String city_state) {
		super();
		this.serial_number = serial_number;
		this.company_name = company_name;
		this.division = division;
		this.city_state = city_state;
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
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getCity_state() {
		return city_state;
	}
	public void setCity_state(String city_state) {
		this.city_state = city_state;
	}
	
}
