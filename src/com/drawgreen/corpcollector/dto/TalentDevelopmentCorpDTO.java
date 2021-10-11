package com.drawgreen.corpcollector.dto;

public class TalentDevelopmentCorpDTO implements CorpDTO{
	private int serial_number;
	private String company_name;
	private String representative;
	private String address;
	private String main_product;
	
	public TalentDevelopmentCorpDTO(int serial_number, String company_name, String representative, String address,
			String main_product) {
		super();
		this.serial_number = serial_number;
		this.company_name = company_name;
		this.representative = representative;
		this.address = address;
		this.main_product = main_product;
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

	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMain_product() {
		return main_product;
	}

	public void setMain_product(String main_product) {
		this.main_product = main_product;
	}
	
	
}
