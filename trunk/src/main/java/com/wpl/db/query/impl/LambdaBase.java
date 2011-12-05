/*
 * Copyright 2010 Kenny Chong (wongpeiling.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wpl.db.query.impl;

import java.lang.reflect.Method;

import javax.persistence.Column;

import ch.lambdaj.Lambda;
import ch.lambdaj.function.argument.Argument;

public class LambdaBase {

	public <E> E on(Class<E> clazz) {
		return Lambda.on(clazz);
	}

	protected String columnName(Object argument) {
		Argument<?> arg = Lambda.argument(argument);
		Class<?> c = arg.getRootArgumentClass();

		String propertyName = arg.getInkvokedPropertyName();
		String methodName = null;

		if (arg.getReturnType() == boolean.class
				|| arg.getReturnType() == Boolean.class) {
			methodName = "is" + propertyName.substring(0, 1).toUpperCase()
					+ propertyName.substring(1);
		} else {
			methodName = "get" + propertyName.substring(0, 1).toUpperCase()
					+ propertyName.substring(1);
		}

		try {
			Method method = c.getMethod(methodName, new Class<?>[0]);

			if (method == null) {
				return propertyName;
			}

			Column column = method.getAnnotation(Column.class);

			if (column == null) {
				return propertyName;
			}

			return column.name();
		} catch (SecurityException e) {
			return propertyName;
		} catch (NoSuchMethodException e) {
			return propertyName;
		}
	}

	protected String className(Object argument) {
		Argument<?> arg = Lambda.argument(argument);
		return arg.getRootArgumentClass().getSimpleName();
	}
}
