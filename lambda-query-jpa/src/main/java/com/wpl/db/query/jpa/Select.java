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
package com.wpl.db.query.jpa;

import java.util.Collection;

import com.wpl.db.query.AbstractQueryBuilder;
import com.wpl.db.query.IQuery;
import com.wpl.db.query.ISelectClause;
import com.wpl.db.query.TableSource;
import com.wpl.db.query.jpa.criteria.Or;

public class Select extends AbstractQueryBuilder implements ISelectClause {

	private final OrderBy mOrder;
	private final Where mWhere;
	private Limit mLimit = null;

	public Select() {
		super(new TableSource());
		mWhere = new Where(getTableSource());
		mOrder = new OrderBy(getTableSource());

	}

	// ~ Select Clause ---------------------------------------------------------

	public void from(final Class<?> clazz) {
		getTableSource().addTable(clazz.getSimpleName());
	}

	// ~ Where Clause ----------------------------------------------------------

	public void isNull(final Object argument) {
		mWhere.isNull(argument);
	}

	public void isNotNull(final Object argument) {
		mWhere.isNotNull(argument);
	}

	public <E> void isEquals(final E argument, final E value) {
		mWhere.isEquals(argument, value);
	}

	public <E> void isNotEquals(final E argument, final E value) {
		mWhere.isNotEquals(argument, value);
	}

	public void like(final Object argument, final String pattern) {
		mWhere.like(argument, pattern);
	}

	public void notLike(final Object argument, final String pattern) {
		mWhere.notLike(argument, pattern);
	}

	public <E> void between(final E argument, final E min, final E max) {
		mWhere.between(argument, min, max);
	}

	public <E> void greaterThan(final E argument, final E value) {
		mWhere.greaterThan(argument, value);
	}

	public <E> void lessThan(final E argument, final E value) {
		mWhere.lessThan(argument, value);
	}

	public void isEmpty(final Collection<?> argument) {
		mWhere.isEmpty(argument);
	}

	public void isNotEmpty(final Collection<?> argument) {
		mWhere.isNotEmpty(argument);
	}

	public <E> void in(final Collection<E> argument, final Collection<E> values) {
		mWhere.in(argument, values);
	}

	public Or or() {
		return new Or(getTableSource());
	}

	// ~ Order By Clause -------------------------------------------------------

	public void orderBy(final Object argument) {
		mOrder.orderBy(argument);
	}

	public void orderByDesc(final Object argument) {
		mOrder.orderByDesc(argument);
	}

	// ~ Limit Clause ----------------------------------------------------------

	public void limit(final Integer first, final Integer max) {
		mLimit = new Limit(first, max);
	}

	public void setParameter(final IQuery query) {
		mWhere.setParameter(query);
		mOrder.setParameter(query);

		if (mLimit != null) {
			mLimit.setParameter(query);
		}
	}

	public String toQuery() {
		final StringBuilder sb = new StringBuilder();
		sb.append("FROM ").append(getTableSource().toString());
		sb.append(mWhere.toQuery());
		sb.append(mOrder.toQuery());
		return sb.toString();
	}
}
