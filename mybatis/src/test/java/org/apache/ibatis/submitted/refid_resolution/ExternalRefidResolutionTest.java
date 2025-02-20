/**
 * Copyright 2009-2020 the original author or authors.
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
package org.apache.ibatis.submitted.refid_resolution;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.Reader;

/**
 * @see <a href="https://github.com/mybatis/old-google-code-issues/issues/291>Issue 291</a>
 *
 */
class ExternalRefidResolutionTest {
	@Test
	void testExternalRefAfterSelectKey() throws Exception {
		String resource = "org/apache/ibatis/submitted/refid_resolution/ExternalMapperConfig.xml";
		try (Reader reader = Resources.getResourceAsReader(resource)) {
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory sqlSessionFactory = builder.build(reader);
			sqlSessionFactory.getConfiguration().getMappedStatementNames();
		}
	}
}
