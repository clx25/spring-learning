package com.learn.mapper;

import com.learn.mapper.SqlProvider.UserMapperSqlProvider;
import com.learn.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
@CacheNamespace
public interface UserMapper {

	@Select("select * from USER where id=#{id} or id=#{id2}")
//	@SelectProvider(value = UserMapperSqlProvider.class,method = "list")
	List<User> list( int id,int id2);
}
