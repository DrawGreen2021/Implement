package com.drawgreen.corpcollector.dto;

public class SocialCorpDTO implements CorpDTO{
	private int serial_number;
	private String organization_name;
	private String business_contents;
	private String realization_type;
	private String representative_number;
	private String location;
	private String homepage;

	public SocialCorpDTO(int serial_number, String organization_name, String business_contents, String realization_type,
			String representative_number, String location, String homepage) {
		super();
		this.serial_number = serial_number;
		this.organization_name = organization_name;
		this.business_contents = business_contents;
		this.realization_type = realization_type;
		this.representative_number = representative_number;
		this.location = location;
		this.homepage = homepage;
	}

	public int getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(int serial_number) {
		this.serial_number = serial_number;
	}

	public String getOrganization_name() {
		return organization_name;
	}

	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}

	public String getBusiness_contents() {
		return business_contents;
	}

	public void setBusiness_contents(String business_contents) {
		this.business_contents = business_contents;
	}

	public String getRealization_type() {
		return realization_type;
	}

	public void setRealization_type(String realization_type) {
		this.realization_type = realization_type;
	}

	public String getRepresentative_number() {
		return representative_number;
	}

	public void setRepresentative_number(String representative_number) {
		this.representative_number = representative_number;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
}
