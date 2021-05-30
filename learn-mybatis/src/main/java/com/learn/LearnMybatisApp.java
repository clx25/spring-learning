package com.learn;


import com.learn.config.MyBatisConfig;
import com.learn.controller.UserController;
import com.learn.mapper.UserMapper;
import com.learn.service.JdbcService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@SpringJUnitConfig(MyBatisConfig.class)
public class LearnMybatisApp {

	public static void main(String[] args) {
//		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\class");
//		System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MyBatisConfig.class);

		UserController userController = ac.getBean("userController", UserController.class);

		userController.list();


//		UserController uc = ac.getBean("userController", UserController.class);
//		uc.list();
//		UserController uc2 = ac.getBean("userController", UserController.class);
//		uc2.list();
	}

	//mybatis的日志初始化位于org.apache.ibatis.logging.LogFactory
	@Test
	public void mybatis() throws IOException {
		//这个设置让xml文件允许访问http，mybatis-config.xml需要从网络获取dtd文件
		System.setProperty("javax.xml.accessExternalDTD", "all");
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//传入xml，获取配置，初始化对应的环境，如果没有该配置，那么就不初始化或使用默认
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//xml中的配置可以用代码的方式替代
//		sqlSessionFactory.getConfiguration().addMapper(UserMapper.class);

		//SqlSession sqlSession = sqlSessionFactory.openSession();

		//SqlSessionManager实现了SqlSessionFactory和SqlSession
		//内部也是使用了SqlSessionFactoryBuilder
		SqlSession sqlSession = SqlSessionManager.newInstance(inputStream).openSession();

		//返回代理对象
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);


	}


	JdbcService service = new JdbcService();

	@Test
	void testJdbc() throws SQLException, ClassNotFoundException {
		service.testJdbc();
	}

	@Test
	void selectAll() throws SQLException {
		service.selectAll();
	}

	@Test
	void testTransaction() throws SQLException {
		service.testTransaction();
	}

}
