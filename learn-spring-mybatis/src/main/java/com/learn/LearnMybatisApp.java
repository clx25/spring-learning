package com.learn;


import com.learn.config.MyBatisConfig;
import com.learn.controller.UserController;
import com.learn.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;
import java.io.InputStream;

@SpringJUnitConfig(MyBatisConfig.class)
public class LearnMybatisApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MyBatisConfig.class);
		UserController uc = ac.getBean("userController", UserController.class);
		uc.list();
		UserController uc2 = ac.getBean("userController", UserController.class);
		uc2.list();
	}

	//mybatis的日志初始化位于org.apache.ibatis.logging.LogFactory
	@Test
	public void mybatis() throws IOException {

		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//在这个方法初始化所有环境
		sqlSessionFactory.getConfiguration().addMapper(UserMapper.class);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//返回代理对象
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		System.out.println(mapper.list());
	}
}
