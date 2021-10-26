package com.drawgreen.corpcollector.dto;

import java.sql.Timestamp;

public class PostDTO {
	private int board_number;
	private String writer_id;
	private String writer_name;
	private String title;
	private String content;
	private Timestamp registration_date;
	private int hits;
	private boolean is_private_writing;
	private boolean is_private_writer;

	public PostDTO(int board_number, String writer_id, String writer_name, String title, String content,
			Timestamp registration_date, int hits, boolean is_private_writing, boolean is_private_writer) {
		super();
		this.board_number = board_number;
		this.writer_id = writer_id;
		this.writer_name = writer_name;
		this.title = title;
		this.content = content;
		this.registration_date = registration_date;
		this.hits = hits;
		this.is_private_writing = is_private_writing;
		this.is_private_writer = is_private_writer;
	}

	public int getBoard_number() {
		return board_number;
	}

	public void setBoard_number(int board_number) {
		this.board_number = board_number;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public String getWriter_name() {
		return writer_name;
	}

	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Timestamp registration_date) {
		this.registration_date = registration_date;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public boolean isIs_private_writing() {
		return is_private_writing;
	}

	public void setIs_private_writing(boolean is_private_writing) {
		this.is_private_writing = is_private_writing;
	}

	public boolean isIs_private_writer() {
		return is_private_writer;
	}

	public void setIs_private_writer(boolean is_private_writer) {
		this.is_private_writer = is_private_writer;
	}
	
}
