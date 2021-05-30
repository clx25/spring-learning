package com.learn.controller;

import com.learn.mapper.UserMapper;
import com.learn.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Component
public class UserController {

	@Resource
	private UserMapper userMapper;

	@Transactional
	public void list(){
		List<User> list = userMapper.list(1,2);
		System.out.println(Arrays.toString(list.toArray()));

	}
}
