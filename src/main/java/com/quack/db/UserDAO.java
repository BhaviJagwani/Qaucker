package com.quack.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.quack.core.User;

@RegisterMapper(UserMapper.class)
public interface UserDAO {
	
	 @SqlQuery("SELECT * FROM users")
	 List<User> allUsers();
	 
	 @SqlQuery("SELECT * FROM quacks where username = :name")
	 User findUser(@Bind("name") String name);

}
