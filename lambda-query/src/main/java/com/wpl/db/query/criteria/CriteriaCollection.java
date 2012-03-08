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
package com.wpl.db.query.criteria;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Query;

import com.wpl.db.query.ITableSource;
import com.wpl.db.query.IWhereClause;
import com.wpl.db.query.jpa.QueryBuilder;

public class CriteriaCollection extends QueryBuilder implements ICriteria,
		IWhereClause {
	private final String mSeparator;

	protected CriteriaCollection(String separator, ITableSource tableSource) {
		super(tableSource);
		this.mSeparator = separator;
	}

	private final ArrayList<ICriteria> mCriteria = new ArrayList<ICriteria>();

	public int count() {
		return mCriteria.size();
	}

	// ~ Implementation of IWhereClause ----------------------------------------

	public void isNull(Object argument) {
		addCriteria(new IsNull(getTableSource(), getTableAlias(argument),
				columnName(argument)));
	}

	public void isNotNull(Object argument) {
		addCriteria(new IsNotNull(getTableSource(), getTableAlias(argument),
				columnName(argument)));
	}

	public <E> void isEquals(E argument, E value) {
		addCriteria(new IsEquals(getTableSource(), getTableAlias(argument),
				columnName(argument), value));
	}

	public <E> void isNotEquals(E argument, E value) {
		addCriteria(new IsNotEquals(getTableSource(), getTableAlias(argument),
				columnName(argument), value));
	}

	public void like(Object argument, String pattern) {
		addCriteria(new Like(getTableSource(), getTableAlias(argument),
				columnName(argument), pattern));
	}

	public void notLike(Object argument, String pattern) {
		addCriteria(new NotLike(getTableSource(), getTableAlias(argument),
				columnName(argument), pattern));
	}

	public void isEmpty(Collection<?> argument) {
		addCriteria(new IsEmpty(getTableSource(), getTableAlias(argument),
				columnName(argument)));
	}

	public void isNotEmpty(Collection<?> argument) {
		addCriteria(new IsNotEmpty(getTableSource(), getTableAlias(argument),
				columnName(argument)));
	}

	public <E> void between(E argument, E min, E max) {
		addCriteria(new Between<E>(getTableSource(), getTableAlias(argument),
				columnName(argument), min, max));
	}

	public <E> void greaterThan(E argument, E value) {
		addCriteria(new GreaterThan<E>(getTableSource(),
				getTableAlias(argument), columnName(argument), value));
	}

	public <E> void lessThan(E argument, E value) {
		addCriteria(new LessThan<E>(getTableSource(), getTableAlias(argument),
				columnName(argument), value));
	}

	public <E> void in(Collection<E> argument, Collection<E> values) {
		addCriteria(new In(getTableSource(), getTableAlias(argument),
				columnName(argument), values));

	}

	// ~ Implementation of IQueryBuilder ---------------------------------------

	public String toQuery() {

		StringBuilder sb = new StringBuilder();

		sb.append("(");

		boolean isFirst = true;

		for (ICriteria c : mCriteria) {

			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(this.mSeparator);
			}

			sb.append(c.toQuery());
		}

		sb.append(")");

		return sb.toString();
	}

	public void setParameter(Query query) {
		for (ICriteria c : mCriteria) {
			c.setParameter(query);
		}
	}

	public CriteriaCollection addCriteria(ICriteria criteria) {
		mCriteria.add(criteria);
		return this;
	}

	@Override
	public String toString() {
		return toQuery();
	}
}
