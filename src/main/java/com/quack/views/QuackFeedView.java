package com.quack.views;

import java.util.List;

import com.quack.core.Quack;

import io.dropwizard.views.View;

public class QuackFeedView extends View{

	private List<Quack> quacks;
	private String username;

	public QuackFeedView(List<Quack> quacks, String username) {
		super("feedAll.mustache");
		this.quacks = quacks;
		this.username = username;
	}

	public List<Quack> quacks(){
		return quacks;
	}

	public String username() {
		return username;
	}
}
