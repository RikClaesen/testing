package com.example.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorDetail {

	private String title;
	
	private int status;

	private String detail;

	private long timeStamp;
	
	private String developerMessage;
	
		
	public void setTitle(String title) {
		this.title = title;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getTitle() {
		return title;
	}

	public int getStatus() {
		return status;
	}

	public String getDetail() {
		return detail;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}
	
	
}
