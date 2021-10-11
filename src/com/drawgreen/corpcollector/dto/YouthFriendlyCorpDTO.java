package com.drawgreen.corpcollector.dto;

public class YouthFriendlyCorpDTO implements CorpDTO{
	private int serial_number;
	private String company_name;
	private String location;
	private String sector;
	private String best_wage;
	private String best_balance;
	private String best_employ;
	
	public YouthFriendlyCorpDTO(int serial_number, String company_name, String location, String sector,
			String best_wage, String best_balance, String best_employ) {
		super();
		this.serial_number = serial_number;
		this.company_name = company_name;
		this.location = location;
		this.sector = sector;
		this.best_wage = best_wage;
		this.best_balance = best_balance;
		this.best_employ = best_employ;
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

	public String getBest_wage() {
		return best_wage;
	}

	public void setBest_wage(String best_wage) {
		this.best_wage = best_wage;
	}

	public String getBest_balance() {
		return best_balance;
	}

	public void setBest_balance(String best_balance) {
		this.best_balance = best_balance;
	}

	public String getBest_employ() {
		return best_employ;
	}

	public void setBest_employ(String best_employ) {
		this.best_employ = best_employ;
	}
	
}
