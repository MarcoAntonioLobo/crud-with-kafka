package com.github.marcoantoniolobo.usercrud.kafka;

public class UserEvent {
	private Long id;
	private String name;
	private String eventType;

	public UserEvent() {
	}

	public UserEvent(Long id, String name, String eventType) {
		this.id = id;
		this.name = name;
		this.eventType = eventType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}
