package com.learn.service;

import java.sql.*;

public class JdbcService {
	private static Connection conn;

	static {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			//
		}
		String url = "jdbc:h2:~/mybatis;MV_STORE=false";
		String user = "root";
		String password = "123456";
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException throwables) {
			//
		}
	}


	public void testJdbc() throws ClassNotFoundException, SQLException {


		Statement statement = conn.createStatement();
		//executeUpdate用来增删改,返回执行成功的条数
		String sql = "update  user set uname='d' where id=1";
		int i = statement.executeUpdate(sql);

		selectAll();
	}

	public void testTransaction() throws SQLException {
		selectAll();

		conn.setAutoCommit(false);
		try (Statement statement = conn.createStatement()) {
			String sql1 = "update  user set uname='4' where id=1";
			String sql2 = "update  user set uname='4' where id=2";

			statement.executeUpdate(sql1);
			int i = 10 / 0;
			statement.executeUpdate(sql2);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.setAutoCommit(true);
			selectAll();
		}


	}

	public void selectAll() throws SQLException {
		Statement statement = conn.createStatement();
		//使用executeQuery执行查询，返回一个结果集
		String sql2 = "select * from USER";
		ResultSet resultSet = statement.executeQuery(sql2);
		printResult(resultSet);
	}

	private void printResult(ResultSet resultSet) throws SQLException {
		//用next判断是否有下一行
		while (resultSet.next()) {
			//通过不同的方法获取一行中不同类型的数据
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			int age = resultSet.getInt(3);
			System.out.printf("id=%d name=%s age=%d\n", id, name, age);
		}
		System.out.println();
	}
}
