package com.zaoqibu.theartofkissing.domain;

import java.io.Serializable;

public class Article implements Serializable
{
	private static final long serialVersionUID = 3722092841146925037L;
	
	private String title;
	private String text;
	private String icon;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
