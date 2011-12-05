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
package com.wpl.db.query.impl;

import java.util.Collection;

import javax.persistence.Query;

import com.wpl.db.query.IDeleteClause;
import com.wpl.db.query.IWhereClause;

public class Delete extends QueryBuilder implements IDeleteClause {

	private final IWhereClause mWhere;

	public Delete() {
		super(new TableSource());
		mWhere = new Where(getTableSource());
	}

	// ~ Implementation of IQueryBuilder ---------------------------------------

	public String toQuery() {

		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ").append(getTableSource().toString());
		sb.append(mWhere.toQuery());
		return sb.toString();
	}

	public void setParameter(Query query) {
		mWhere.setParameter(query);
	}

	// ~ Implementation of IDeleteClause ---------------------------------------

	public void delete(Class<?> clazz) {
		getTableSource().addTable(clazz.getSimpleName());
	}

	// ~ Implementation of IWhereClause ----------------------------------------

	public void isNull(Object argument) {
		mWhere.isNull(argument);
	}

	public void isNotNull(Object argument) {
		mWhere.isNotNull(argument);
	}

	public <E> void isEquals(E argument, E value) {
		mWhere.isEquals(argument, value);
	}

	public <E> void isNotEquals(E argument, E value) {
		mWhere.isEquals(argument, value);
	}

	public <E> void between(E argument, E min, E max) {
		mWhere.between(argument, min, max);
	}

	public <E> void greaterThan(E argument, E value) {
		mWhere.greaterThan(argument, value);
	}

	public <E> void lessThan(E argument, E value) {
		mWhere.lessThan(argument, value);
	}

	public void like(Object argument, String pattern) {
		mWhere.like(argument, pattern);
	}

	public void notLike(Object argument, String pattern) {
		mWhere.like(argument, pattern);
	}

	public void isEmpty(Collection<?> argument) {
		mWhere.isEmpty(argument);
	}

	public void isNotEmpty(Collection<?> argument) {
		mWhere.isNotEmpty(argument);
	}

	public <E> void in(Collection<E> argument, Collection<E> values) {
		mWhere.in(argument, values);
	}
}
