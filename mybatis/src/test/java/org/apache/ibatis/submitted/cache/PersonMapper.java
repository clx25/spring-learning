/**
 * Copyright 2009-2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ibatis.submitted.cache;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;

import java.util.List;

@CacheNamespace
public interface PersonMapper {

	@Insert("insert into person (id, firstname, lastname) values (#{id}, #{firstname}, #{lastname})")
	void create(Person person);

	@Insert("insert into person (id, firstname, lastname) values (#{id}, #{firstname}, #{lastname})")
	@Options
	void createWithOptions(Person person);

	@Insert("insert into person (id, firstname, lastname) values (#{id}, #{firstname}, #{lastname})")
	@Options(flushCache = FlushCachePolicy.FALSE)
	void createWithoutFlushCache(Person person);

	@Delete("delete from person where id = #{id}")
	void delete(int id);

	@Select("select id, firstname, lastname from person")
	List<Person> findAll();

	@Select("select id, firstname, lastname from person")
	@Options(flushCache = FlushCachePolicy.TRUE)
	List<Person> findWithFlushCache();
}
