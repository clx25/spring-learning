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
package org.apache.ibatis.submitted.substitution_in_annots;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SubstitutionInAnnotsMapper {

	String getPersonNameByIdWithXml(int id);

	@Select("select firstName from ibtest.names where id=${value}")
	String getPersonNameByIdWithAnnotsValue(int id);

	@Select("select firstName from ibtest.names where id=${_parameter}")
	String getPersonNameByIdWithAnnotsParameter(int id);

	@Select("select firstName from ibtest.names where id=${named}")
	String getPersonNameByIdWithAnnotsParamAnnot(@Param("named") int id);

}
