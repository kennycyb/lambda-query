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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

public class Update extends QueryBuilder implements IUpdateClause {

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

	public void update(Class<?> clazz) {
		getTableSource().addTable(clazz.getSimpleName());
	}

	public <E> void set(final E argument, final E value) {

		UpdateItem item = new UpdateItem();

		item.table = getTableAlias(argument);
		item.column = columnName(argument);
		item.value = value;

		mItems.add(item);
	}

	// ~ Implementation of IQueryBuilder ---------------------------------------

	public String toQuery() {

		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ").append(getTableSource().toString());
		sb.append(" SET ");

		boolean isFirst = true;

		for (UpdateItem item : mItems) {
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

	public void setParameter(Query query) {

		for (UpdateItem item : mItems) {
			query.setParameter(item.column, item.value);
		}

		mWhere.setParameter(query);
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
		mWhere.isNotEquals(argument, value);
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
		mWhere.notLike(argument, pattern);
	}

	public void isEmpty(Collection<?> argument) {
		mWhere.isEmpty(argument);
	}

}
