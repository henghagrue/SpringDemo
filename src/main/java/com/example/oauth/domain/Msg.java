package com.example.oauth.domain;

public class Msg {
	private String title;
	private String content;
	private String etraInfo;
	
	public Msg(String title, String content, String etraInfo) {
		super();
		this.title = title;
		this.content = content;
		this.etraInfo = etraInfo;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the etraInfo
	 */
	public String getEtraInfo() {
		return etraInfo;
	}
	/**
	 * @param etraInfo the etraInfo to set
	 */
	public void setEtraInfo(String etraInfo) {
		this.etraInfo = etraInfo;
	}
	
}
