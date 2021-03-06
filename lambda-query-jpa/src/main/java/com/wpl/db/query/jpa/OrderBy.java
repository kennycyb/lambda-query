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

import com.wpl.db.query.AbstractQueryBuilder;
import com.wpl.db.query.IOrderClause;
import com.wpl.db.query.IQuery;
import com.wpl.db.query.ITableSource;

public class OrderBy extends AbstractQueryBuilder implements IOrderClause {

	private final class OrderByItem {
		public String clazz;
		public String name;
		public boolean isDecending;
	}

	public OrderBy() {
		this(null);
	}

	public OrderBy(final ITableSource tableSource) {
		super(tableSource);
	}

	private final ArrayList<OrderByItem> mItems = new ArrayList<OrderByItem>();

	public void orderBy(final Object argument) {

		final OrderByItem item = new OrderByItem();
		item.clazz = getTableAlias(argument);
		item.isDecending = false;
		item.name = columnName(argument);

		mItems.add(item);
	}

	public void orderByDesc(final Object argument) {

		final OrderByItem item = new OrderByItem();
		item.clazz = getTableAlias(argument);
		item.isDecending = true;
		item.name = columnName(argument);

		mItems.add(item);
	}

	public String toQuery() {

		if (mItems.size() == 0) {
			return "";
		}

		boolean isFirst = true;

		final StringBuilder sb = new StringBuilder();

		sb.append(" ORDER BY ");

		for (final OrderByItem arg : mItems) {

			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(",");
			}

			if (arg.clazz != null) {
				sb.append(arg.clazz).append(".");
			}

			sb.append(arg.name);

			if (arg.isDecending) {
				sb.append(" DESC");
			}
		}

		return sb.toString();
	}

	public void setParameter(final IQuery query) {
		// DO NOTHING
	}

}
