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

import javax.persistence.Query;

import com.wpl.db.query.ITableSource;
import com.wpl.db.query.QueryBuilder;

public class CriteriaCollection extends QueryBuilder implements ICriteria {
	private final String mSeparator;

	protected CriteriaCollection(String separator, ITableSource tableSource) {
		super(tableSource);
		this.mSeparator = separator;
	}

	private final ArrayList<ICriteria> mCriteria = new ArrayList<ICriteria>();

	public int count() {
		return mCriteria.size();
	}

	public void isNull(Object argument) {
		addCriteria(new IsNull(getTableAlias(argument), columnName(argument)));
	}

	public <E> void isEquals(E argument, E value) {
		addCriteria(new IsEquals(getTableAlias(argument), columnName(argument),
				value));
	}

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
