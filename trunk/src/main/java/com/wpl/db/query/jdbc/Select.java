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
package com.wpl.db.query.jdbc;

import java.util.Collection;

import javax.persistence.Query;

import com.wpl.db.query.ISelectClause;
import com.wpl.db.query.jpa.QueryBuilder;
import com.wpl.db.query.jpa.TableSource;

public class Select extends QueryBuilder implements ISelectClause {

	protected Select() {
		super(new TableSource());
	}

	// ~ Implementation of ISelectClause ---------------------------------------

	public void from(Class<?> clazz) {
		getTableSource().addTable(clazz.getSimpleName());
	}

	public void isNull(Object argument) {
		// TODO Auto-generated method stub

	}

	public void isNotNull(Object argument) {
		// TODO Auto-generated method stub

	}

	public <E> void isEquals(E argument, E value) {
		// TODO Auto-generated method stub

	}

	public <E> void isNotEquals(E argument, E value) {
		// TODO Auto-generated method stub

	}

	public <E> void between(E argument, E min, E max) {
		// TODO Auto-generated method stub

	}

	public <E> void greaterThan(E argument, E value) {
		// TODO Auto-generated method stub

	}

	public <E> void lessThan(E argument, E value) {
		// TODO Auto-generated method stub

	}

	public void like(Object argument, String pattern) {
		// TODO Auto-generated method stub

	}

	public void notLike(Object argument, String pattern) {
		// TODO Auto-generated method stub

	}

	public void isEmpty(Collection<?> argument) {
		// TODO Auto-generated method stub

	}

	public void isNotEmpty(Collection<?> argument) {
		// TODO Auto-generated method stub

	}

	public <E> void in(Collection<E> argument, Collection<E> values) {
		// TODO Auto-generated method stub

	}

	public String toQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setParameter(Query query) {
		// TODO Auto-generated method stub

	}

	public void orderBy(Object argument) {
		// TODO Auto-generated method stub

	}

	public void orderByDesc(Object argument) {
		// TODO Auto-generated method stub

	}

	public void limit(Integer first, Integer max) {
		// TODO Auto-generated method stub

	}

}
