/*
 * Copyright 2011,2012 Kenny Chong (wongpeiling.com)
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

import com.wpl.db.query.IQuery;
import com.wpl.db.query.ITableSource;

public abstract class AbstractOneParamCriteria<E> extends Criteria {

	private final E mValue;
	private final String mParamName;
	private final String mOperator;

	public AbstractOneParamCriteria(ITableSource tableSource, String table,
			String column, E value, String operator) {
		super(tableSource, table, column);
		this.mValue = value;
		this.mParamName = getNextParamName();
		this.mOperator = operator;
	}

	public final String getParamName() {
		return mParamName;
	}

	public final E getValue() {
		return mValue;
	}

	public final void setParameter(IQuery query) {
		query.setParameter(getParamName(), getValue());
	}

	public String getOperator() {
		return mOperator;
	}

	public String toQuery() {

		if (getTable() == null) {
			return String.format("%s %s :%s", getColumn(), getOperator(),
					getParamName());
		}

		return String.format("%s.%s %s :%s", getTable(), getColumn(),
				getOperator(), getParamName());
	}
}
