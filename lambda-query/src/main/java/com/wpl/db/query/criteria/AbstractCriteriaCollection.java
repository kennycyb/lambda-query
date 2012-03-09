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

import com.wpl.db.query.AbstractQueryBuilder;
import com.wpl.db.query.ITableSource;
import com.wpl.db.query.IWhereClause;

public abstract class AbstractCriteriaCollection extends AbstractQueryBuilder
		implements ICriteria, IWhereClause {
	private final String mSeparator;

	protected AbstractCriteriaCollection(String separator,
			ITableSource tableSource) {
		super(tableSource);
		this.mSeparator = separator;
	}

	private final ArrayList<ICriteria> mCriteria = new ArrayList<ICriteria>();

	public int count() {
		return mCriteria.size();
	}

	protected ArrayList<ICriteria> getCriterias() {
		return mCriteria;
	}

	// ~ Implementation of IWhereClause ----------------------------------------

	public final void isNull(Object argument) {
		addCriteria(isNull(getTableSource(), getTableAlias(argument),
				columnName(argument)));
	}

	public final void isNotNull(Object argument) {
		addCriteria(isNotNull(getTableSource(), getTableAlias(argument),
				columnName(argument)));
	}

	public <E> void isNotEquals(E argument, E value) {
		addCriteria(isNotEquals(getTableSource(), getTableAlias(argument),
				columnName(argument), value));
	}

	public <E> void isEquals(final E argument, final E value) {
		addCriteria(isEquals(getTableSource(), getTableAlias(argument),
				columnName(argument), value));
	}

	public void isEmpty(final Collection<?> argument) {
		addCriteria(isEmpty(getTableSource(), getTableAlias(argument),
				columnName(argument)));
	}

	public void like(Object argument, String pattern) {
		addCriteria(like(getTableSource(), getTableAlias(argument),
				columnName(argument), pattern));
	}

	public void notLike(Object argument, String pattern) {
		addCriteria(notLike(getTableSource(), getTableAlias(argument),
				columnName(argument), pattern));
	}

	public void isNotEmpty(Collection<?> argument) {
		addCriteria(isNotEmpty(getTableSource(), getTableAlias(argument),
				columnName(argument)));
	}

	public <E> void between(E argument, E min, E max) {
		addCriteria(between(getTableSource(), getTableAlias(argument),
				columnName(argument), min, max));
	}

	public <E> void greaterThan(E argument, E value) {
		addCriteria(greaterThan(getTableSource(), getTableAlias(argument),
				columnName(argument), value));
	}

	public <E> void lessThan(E argument, E value) {
		addCriteria(lessThan(getTableSource(), getTableAlias(argument),
				columnName(argument), value));
	}

	public <E> void in(Collection<E> argument, Collection<E> values) {
		addCriteria(in(getTableSource(), getTableAlias(argument),
				columnName(argument), values));

	}

	protected abstract <E> ICriteria in(ITableSource tableSource, String table,
			String column, Collection<E> values);

	protected abstract <E> ICriteria between(ITableSource tableSource,
			String table, String column, E min, E max);

	protected abstract ICriteria notLike(final ITableSource tableSource,
			final String table, final String column, final String pattern);

	protected abstract ICriteria isNotEmpty(final ITableSource tableSource,
			final String table, final String column);

	protected abstract <E> ICriteria greaterThan(
			final ITableSource tableSource, final String table,
			final String column, final E value);

	protected abstract <E> ICriteria lessThan(final ITableSource tableSource,
			final String table, final String column, final E value);

	protected abstract ICriteria isEmpty(final ITableSource tableSource,
			final String table, final String column);

	protected abstract <E> ICriteria isEquals(final ITableSource tableSource,
			final String table, final String column, final E value);

	protected abstract ICriteria isNull(final ITableSource tableSource,
			final String table, final String column);

	protected abstract ICriteria isNotNull(final ITableSource tableSource,
			final String table, final String column);

	protected abstract <E> ICriteria isNotEquals(
			final ITableSource tableSource, final String table,
			final String column, final E value);

	protected abstract ICriteria like(final ITableSource tableSource,
			final String table, final String column, final String pattern);

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

	public AbstractCriteriaCollection addCriteria(ICriteria criteria) {
		mCriteria.add(criteria);
		return this;
	}

	@Override
	public String toString() {
		return toQuery();
	}
}
