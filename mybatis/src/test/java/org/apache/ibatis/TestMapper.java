package org.apache.ibatis;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TestMapper  {

  @Select("select * from USER ")
  List<User> query();
}
