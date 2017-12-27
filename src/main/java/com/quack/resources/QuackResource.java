package com.quack.resources;

import java.time.LocalDateTime;
import java.util.List;

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

@Path("quacks")
public class QuackResource {

	private final QuackDAO quackDAO;
	
	public QuackResource(QuackDAO dao) {
		this.quackDAO = dao;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Quack> allQuacks(){
		return quackDAO.listQuacks();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
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
