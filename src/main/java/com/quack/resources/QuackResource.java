package com.quack.resources;

import java.time.LocalDateTime;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.http.HttpStatus;
import org.skife.jdbi.v2.sqlobject.BindBean;

import com.quack.core.Quack;
import com.quack.core.User;
import com.quack.db.QuackDAO;
import com.quack.views.QuackFeedView;

import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.sessions.Session;

@Path("quacks")
@PermitAll
@Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
public class QuackResource {

	private final QuackDAO quackDAO;
	
	public QuackResource(QuackDAO dao) {
		this.quackDAO = dao;
	}

	@GET
	public QuackFeedView allQuacks(@Auth User user){
		QuackFeedView view = new QuackFeedView(quackDAO.listQuacks(), user.getName());
		return view;
	}
	
	@GET
	@Path("/{id}")
	public Quack findQuack(@PathParam("id") int id){
		return quackDAO.findQuack(id);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public int quack(@Session HttpSession session, @BindBean Quack quack){
		quack.setTimestamp(LocalDateTime.now());
		quackDAO.insertBean(quack);
		return HttpStatus.CREATED_201;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void quack(@Session HttpSession session, @FormParam("message") String text) {
		Quack quack = new Quack((Long)session.getAttribute("userId"), text, LocalDateTime.now());
		quackDAO.insertBean(quack);
	}
}
