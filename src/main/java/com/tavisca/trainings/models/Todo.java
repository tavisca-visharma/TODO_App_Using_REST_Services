package com.tavisca.trainings.models;

public class Todo {
	
	private int id;
	private String timeStamp;
	private String content;
	private boolean isChecked;
	static int counter = 0;
	
	public Todo(String timeStamp, String content, boolean isChecked) {
		super();
		this.id = counter++;
		this.timeStamp = timeStamp;
		this.content = content;
		this.isChecked = isChecked;
	}
	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}
	

	public String getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public boolean isChecked() {
		return isChecked;
	}


	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}


	@Override
	public String toString() {
		return "Todo [timeStamp=" + timeStamp + ", content=" + content + ", isChecked=" + isChecked + "]";
	}

}
