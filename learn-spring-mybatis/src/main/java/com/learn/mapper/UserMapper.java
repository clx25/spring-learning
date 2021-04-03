package com.learn.mapper;

import com.learn.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;



public interface UserMapper {

	@Select("select * from USER")
	List<User> list();
}
