package com.quack.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.quack.core.User;

public class UserMapper implements ResultSetMapper<User>{

	@Override
	public User map(int index, ResultSet result, StatementContext ctx) throws SQLException {
		return new User(result.getLong("userid"), result.getString("username"));
	}
}