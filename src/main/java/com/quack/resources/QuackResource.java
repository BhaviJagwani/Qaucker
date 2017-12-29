package com.quack.resources;

import java.time.LocalDateTime;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.skife.jdbi.v2.sqlobject.BindBean;

import com.quack.api.Quack;
import com.quack.db.QuackDAO;
import com.quack.views.QuackFeedView;

@Path("quacks")
@Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
public class QuackResource {

	private final QuackDAO quackDAO;
	
	public QuackResource(QuackDAO dao) {
		this.quackDAO = dao;
	}

	@GET
	public QuackFeedView allQuacks(){
		QuackFeedView view = new QuackFeedView(quackDAO.listQuacks());
		return view;
	}
	
	@GET
	@Path("/{id}")
	public Quack findQuack(@PathParam("id") int id){
		return quackDAO.findQuack(id);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public void quack(@BindBean Quack quack){
		quack.setTimestamp(LocalDateTime.now());
		quackDAO.insertBean(quack);
	}
}
