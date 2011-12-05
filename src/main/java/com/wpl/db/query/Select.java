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

import javax.persistence.Query;

import com.wpl.db.query.criteria.Or;

public class Select extends QueryBuilder implements ISelectClause {

	private final OrderBy mOrder;
	private final Where mWhere;
	private Limit mLimit = null;

	public Select() {
		super(new TableSource());
		mWhere = new Where(getTableSource());
		mOrder = new OrderBy(getTableSource());

	}

	// ~ Select Clause ---------------------------------------------------------

	public void from(Class<?> clazz) {
		getTableSource().addTable(clazz.getSimpleName());
	}

	// ~ Where Clause ----------------------------------------------------------

	public void isNull(Object argument) {
		mWhere.isNull(argument);
	}

	public <E> void isEquals(E argument, E value) {
		mWhere.isEquals(argument, value);
	}

	public void like(Object argument, String pattern) {
		mWhere.like(argument, pattern);
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

	public void isEmpty(Collection<?> argument) {
		mWhere.isEmpty(argument);
	}

	public Or or() {
		return new Or(getTableSource());
	}

	// ~ Order By Clause -------------------------------------------------------

	public void orderBy(Object argument) {
		mOrder.orderBy(argument);
	}

	public void orderByDesc(Object argument) {
		mOrder.orderByDesc(argument);
	}

	// ~ Limit Clause ----------------------------------------------------------

	public void limit(Integer first, Integer max) {
		mLimit = new Limit(first, max);
	}

	public void setParameter(Query query) {
		mWhere.setParameter(query);
		mOrder.setParameter(query);

		if (mLimit != null) {
			mLimit.setParameter(query);
		}
	}

	public String toQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM ").append(getTableSource().toString());
		sb.append(mWhere.toQuery());
		sb.append(mOrder.toQuery());
		return sb.toString();
	}
}
