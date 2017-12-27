package com.quack.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.quack.api.Quack;

public class QuackMapper implements ResultSetMapper<Quack>{

	@Override
	public Quack map(int index, ResultSet result, StatementContext ctx) throws SQLException {
		return new Quack(result.getLong("userId"), result.getString("text"), result.getTimestamp("timestamp").toLocalDateTime());
	}

}
