/*
 * Copyright 2011 Kenny Chong (wongpeiling.com)
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
package com.wpl.db.query;

import java.util.Collection;

public interface IWhereClause extends IQueryBuilder {

	/**
	 * 
	 * @param argument
	 */
	void isNull(Object argument);

	void isNotNull(Object argument);

	/**
	 * 
	 * @param argument
	 * @param value
	 */
	<E> void isEquals(E argument, E value);

	<E> void isNotEquals(E argument, E value);

	<E> void between(E argument, E min, E max);

	<E> void greaterThan(E argument, E value);

	<E> void lessThan(E argument, E value);

	void like(Object argument, String pattern);

	void notLike(Object argument, String pattern);

	void isEmpty(Collection<?> argument);

	void isNotEmpty(Collection<?> argument);

	<E> void in(Collection<E> argument, Collection<E> values);
}
