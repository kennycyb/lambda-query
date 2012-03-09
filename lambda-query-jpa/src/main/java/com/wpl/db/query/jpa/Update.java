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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.wpl.db.query.AbstractQueryBuilder;
import com.wpl.db.query.IQuery;
import com.wpl.db.query.IUpdateClause;
import com.wpl.db.query.IWhereClause;
import com.wpl.db.query.TableSource;

public class Update extends AbstractQueryBuilder implements IUpdateClause {

	class UpdateItem {
		public String table;
		public String column;
		public Object value;
	}

	private final IWhereClause mWhere;
	private final List<UpdateItem> mItems;

	public Update() {
		super(new TableSource());
		mWhere = new Where(getTableSource());
		mItems = new ArrayList<UpdateItem>();
	}

	// ~ Implementation of IUpdateClause ---------------------------------------

	public void update(final Class<?> clazz) {
		getTableSource().addTable(clazz.getSimpleName());
	}

	public <E> void set(final E argument, final E value) {

		final UpdateItem item = new UpdateItem();

		item.table = getTableAlias(argument);
		item.column = columnName(argument);
		item.value = value;

		mItems.add(item);
	}

	// ~ Implementation of IQueryBuilder ---------------------------------------

	public String toQuery() {

		final StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ").append(getTableSource().toString());
		sb.append(" SET ");

		boolean isFirst = true;

		for (final UpdateItem item : mItems) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(",");
			}

			sb.append(item.table).append(".").append(item.column).append("=:")
					.append(item.column);
		}

		sb.append(mWhere.toQuery());

		return sb.toString();
	}

	public void setParameter(final IQuery query) {

		for (final UpdateItem item : mItems) {
			query.setParameter(item.column, item.value);
		}

		mWhere.setParameter(query);
	}

	// ~ Implementation of IWhereClause ----------------------------------------

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

	public <E> void between(final E argument, final E min, final E max) {
		mWhere.between(argument, min, max);
	}

	public <E> void greaterThan(final E argument, final E value) {
		mWhere.greaterThan(argument, value);
	}

	public <E> void lessThan(final E argument, final E value) {
		mWhere.lessThan(argument, value);
	}

	public void like(final Object argument, final String pattern) {
		mWhere.like(argument, pattern);
	}

	public void notLike(final Object argument, final String pattern) {
		mWhere.notLike(argument, pattern);
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

}
