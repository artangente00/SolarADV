package com.PlayGroundAdv.Solar.model;

public class NotificationRequest {

	private Long id;
	private String title;
	private String user;
	private String time;
	private String subject;
	private String message;
	private String status;

	public NotificationRequest() {
		super();
	}

	public NotificationRequest(Long id, String title, String user, String time, String subject, String message,
			String status) {
		super();
		this.id = id;
		this.title = title;
		this.user = user;
		this.time = time;
		this.subject = subject;
		this.message = message;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "NotificationRequest [id=" + id + ", title=" + title + ", user=" + user + ", time=" + time + ", subject="
				+ subject + ", message=" + message + ", status=" + status + "]";
	}

}
