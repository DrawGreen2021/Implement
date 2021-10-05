package com.drawgreen.corpcollector.dto;

public class GreenCorpDTO {
	private int serial_number;
	private String company_name;
	private String location;
	private String sector;
	private String note;
	private String region;
	private String site;
	
	public GreenCorpDTO(int serial_number, String company_name, String location, String sector, String note,
			String region, String site) {
		super();
		this.serial_number = serial_number;
		this.company_name = company_name;
		this.location = location;
		this.sector = sector;
		this.note = note;
		this.region = region;
		this.site = site;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	
	
}
