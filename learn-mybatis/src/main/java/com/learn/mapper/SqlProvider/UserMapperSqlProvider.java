package com.learn.mapper.SqlProvider;

public class UserMapperSqlProvider {
	public String list(int id2,int id1){
		return "select * from user where id="+id2+" or id="+id1;
	}
}
