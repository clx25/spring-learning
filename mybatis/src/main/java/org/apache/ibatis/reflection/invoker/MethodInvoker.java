/**
 * Copyright 2009-2018 the original author or authors.
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
package org.apache.ibatis.reflection.invoker;

import org.apache.ibatis.reflection.Reflector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Clinton Begin
 */
public class MethodInvoker implements Invoker {

	private final Class<?> type;
	private final Method method;

	public MethodInvoker(Method method) {
		this.method = method;

		if (method.getParameterTypes().length == 1) {
			type = method.getParameterTypes()[0];
		} else {
			type = method.getReturnType();
		}
	}

	@Override
	public Object invoke(Object target, Object[] args) throws IllegalAccessException, InvocationTargetException {
		try {
			return method.invoke(target, args);
		} catch (IllegalAccessException e) {
			if (Reflector.canControlMemberAccessible()) {
				method.setAccessible(true);
				return method.invoke(target, args);
			} else {
				throw e;
			}
		}
	}

	@Override
	public Class<?> getType() {
		return type;
	}
}
