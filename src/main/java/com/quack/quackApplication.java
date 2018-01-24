package com.quack;

import org.eclipse.jetty.server.session.SessionHandler;
import org.skife.jdbi.v2.DBI;

import com.quack.core.BasicAuthenticator;
import com.quack.core.User;
import com.quack.db.QuackDAO;
import com.quack.db.UserDAO;
import com.quack.resources.QuackResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import java.net.URISyntaxException;
import java.util.EnumSet;

import javax.servlet.SessionTrackingMode;

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
		final UserDAO userDao = jdbi.onDemand(UserDAO.class);
		
		SessionHandler sessionHandler = new SessionHandler();
		sessionHandler.setHttpOnly(true);
		sessionHandler.setMaxInactiveInterval(120);
		sessionHandler.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
		environment.servlets().setSessionHandler(sessionHandler);
		
		environment.jersey().register(new QuackResource(dao));
		AuthDynamicFeature authDynamicFeature = new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
				.setAuthenticator(new BasicAuthenticator(userDao)).buildAuthFilter());
		environment.jersey().register(authDynamicFeature);
		 environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
	}

}
