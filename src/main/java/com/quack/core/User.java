package com.quack.core;

import java.security.Principal;

public class User implements Principal {

	private final String name;
	
	private final long id;

	public User(long id, String name) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public long getId() {
		return id;
	}

}
