package com.quack.core;

import java.util.Optional;

import com.quack.db.UserDAO;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {

	private final UserDAO userDAO;
	
	public BasicAuthenticator(UserDAO userDao) {
		this.userDAO = userDao;
	}

	@Override
	public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
		if ("secret".equals(credentials.getPassword())) {
			return Optional.of(userDAO.findUser(credentials.getUsername()));
		}
		return Optional.empty();
	}

}
