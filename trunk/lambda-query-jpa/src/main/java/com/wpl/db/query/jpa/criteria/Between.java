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
package com.wpl.db.query.jpa.criteria;

import com.wpl.db.query.IQuery;
import com.wpl.db.query.ITableSource;
import com.wpl.db.query.criteria.Criteria;

public class Between<E> extends Criteria {

	private final E mMin;
	private final E mMax;

	private final String mMinParamName;
	private final String mMaxParamName;

	public Between(final ITableSource tableSource, final String table,
			final String column, final E min, final E max) {
		super(tableSource, table, column);
		this.mMin = min;
		this.mMax = max;

		this.mMinParamName = getNextParamName();
		this.mMaxParamName = getNextParamName();
	}

	public E getMin() {
		return mMin;
	}

	public E getMax() {
		return mMax;
	}

	public String getMinParamName() {
		return mMinParamName;
	}

	public String getMaxParamName() {
		return mMaxParamName;
	}

	public void setParameter(final IQuery query) {
		query.setParameter(getMinParamName(), getMin());
		query.setParameter(getMaxParamName(), getMax());
	}

	public String toQuery() {
		if (getTable() == null) {
			return String.format("%s BETWEEN :%s AND :%s", getColumn(),
					getMinParamName(), getMaxParamName());
		}

		return String.format("%s.%s BETWEEN :%s AND :%s", getTable(),
				getColumn(), getMinParamName(), getMaxParamName());
	}
}
