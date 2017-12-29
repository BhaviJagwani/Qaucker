package com.quack;

import org.skife.jdbi.v2.DBI;

import com.quack.db.QuackDAO;
import com.quack.resources.QuackResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import java.net.URISyntaxException;

public class quackApplication extends Application<quackConfiguration> {

	public static void main(final String[] args) throws Exception {
		new quackApplication().run(args);
	}

	@Override
	public String getName() {
		return "quack";
	}

	@Override
	public void initialize(final Bootstrap<quackConfiguration> bootstrap) {
		 bootstrap.addBundle(new ViewBundle());
		 bootstrap.addBundle(new AssetsBundle("/assets/css", "/css", null, "css"));
	}

	@Override
	public void run(final quackConfiguration configuration, final Environment environment) throws URISyntaxException {
		final DBIFactory factory = new DBIFactory();
	    final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
	    final QuackDAO dao = jdbi.onDemand(QuackDAO.class);
	    environment.jersey().register(new QuackResource(dao));
	}

}
