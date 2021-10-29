package com.drawgreen.corpcollector.dto;

public class NewsDTO {
	private String title;
	private String source;
	private String link;
	private String subTitle;
	
	public NewsDTO(String title, String source, String link, String subTitle) {
		super();
		this.title = title;
		this.source = source;
		this.link = link;
		this.subTitle = subTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	
}
