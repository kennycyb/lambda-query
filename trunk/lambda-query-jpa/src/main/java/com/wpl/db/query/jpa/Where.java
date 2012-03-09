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

import org.apache.commons.lang.StringUtils;

import com.wpl.db.query.AbstractQueryBuilder;
import com.wpl.db.query.IQuery;
import com.wpl.db.query.ITableSource;
import com.wpl.db.query.IWhereClause;
import com.wpl.db.query.jpa.criteria.And;

public class Where extends AbstractQueryBuilder implements IWhereClause {

	private final And mAnd;

	public Where(final ITableSource tableSource) {
		super(tableSource);
		mAnd = new And(tableSource);
	}

	public Where() {
		this(null);
	}

	// ~ Implementation of IWhereClause ----------------------------------------

	public void isNull(final Object argument) {
		mAnd.isNull(argument);
	}

	public void isNotNull(final Object argument) {
		mAnd.isNotNull(argument);
	}

	public <E> void isEquals(final E argument, final E value) {
		mAnd.isEquals(argument, value);
	}

	public <E> void isNotEquals(final E argument, final E value) {
		mAnd.isNotEquals(argument, value);
	}

	public void like(final Object argument, final String pattern) {
		mAnd.like(argument, pattern);
	}

	public void notLike(final Object argument, final String pattern) {
		mAnd.notLike(argument, pattern);
	}

	public <E> void between(final E argument, final E min, final E max) {
		mAnd.between(argument, min, max);
	}

	public void isEmpty(final Collection<?> argument) {
		mAnd.isEmpty(argument);
	}

	public void isNotEmpty(final Collection<?> argument) {
		mAnd.isNotEmpty(argument);
	}

	public <E> void greaterThan(final E argument, final E value) {
		mAnd.greaterThan(argument, value);
	}

	public <E> void lessThan(final E argument, final E value) {
		mAnd.lessThan(argument, value);
	}

	public <E> void in(final Collection<E> argument, final Collection<E> values) {
		mAnd.in(argument, values);
	}

	// ~ Implementation of IQueryBuilder ---------------------------------------

	public void setParameter(final IQuery query) {
		mAnd.setParameter(query);
	}

	public String toQuery() {
		return mAnd.count() == 0 ? StringUtils.EMPTY : String.format(
				" WHERE %s", mAnd.toQuery());
	}
}
