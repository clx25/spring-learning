package org.apache.ibatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Mytest {


	@Test
	public void query() throws IOException {
//    DataSource dataSource = new PooledDataSource("org.h2.Driver", "jdbc:h2:~/mybatis", "root", "123456");
//
//    TransactionFactory transactionFactory = new JdbcTransactionFactory();
//    Environment environment = new Environment("development", transactionFactory, dataSource);
//    Configuration configuration = new Configuration(environment);
//    configuration.addMapper(TestMapper.class);
//    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
//
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		sqlSessionFactory.getConfiguration().addMapper(TestMapper.class);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		TestMapper mapper = sqlSession.getMapper(TestMapper.class);
		List<User> query = mapper.query();
		System.out.println(query);
		List<User> query2 = mapper.query();
		System.out.println(query2);
	}
}
