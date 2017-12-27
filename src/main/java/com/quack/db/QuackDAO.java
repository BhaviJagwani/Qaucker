package com.quack.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.quack.api.Quack;

@RegisterMapper(QuackMapper.class)
public interface QuackDAO {

	@SqlUpdate("INSERT INTO quacks(userId, text, timestamp) VALUES (:userId, :text, :timestamp)")
	void insertBean(@BindBean Quack quack);
	
	 @SqlQuery("SELECT * FROM quacks")
	 List<Quack> listQuacks();
	 
	 @SqlQuery("SELECT * FROM quacks where id = :id")
	 Quack findQuack(@Bind("id")int id);
}