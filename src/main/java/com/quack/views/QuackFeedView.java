package com.quack.views;

import java.util.List;

import com.quack.api.Quack;

import io.dropwizard.views.View;

public class QuackFeedView extends View{

	private List<Quack> quacks;

	public QuackFeedView(List<Quack> quacks) {
		super("feedAll.mustache");
		this.quacks = quacks;
	}

	public List<Quack> quacks(){
		return quacks;
	}

}
