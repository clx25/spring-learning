package org.apache.ibatis;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TestMapper {

	@Select("select * from USER ")
	List<User> query();
}
