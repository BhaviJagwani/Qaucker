package com.quack.api;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Quack {

	private long id;
	
	@JsonProperty
	@NotNull
	private long userId;
	
	@JsonProperty
	@NotNull
	private String text;
	
	@JsonProperty
	private LocalDateTime timestamp;

	public Quack(long userId, String text, LocalDateTime date) {
		super();
		this.userId = userId;
		this.text = text;
		this.timestamp = date;
	}
	
	public Quack() {
        // Jackson deserialization
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime localDateTime) {
		this.timestamp = localDateTime;
	}
	
	
}
