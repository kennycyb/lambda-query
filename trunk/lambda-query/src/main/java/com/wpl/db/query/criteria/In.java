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

import java.util.Collection;

import javax.persistence.Query;

import com.wpl.db.query.ITableSource;

public class In extends Criteria {

	private final Collection<?> mValues;
	private final String mParamName;

	public In(ITableSource tableSource, String table, String column,
			Collection<?> values) {
		super(tableSource, table, column);
		mValues = values;
		mParamName = getNextParamName();
	}

	// ~ Implementation of IQueryBuilder ---------------------------------------

	public void setParameter(Query query) {
		// TODO Auto-generated method stub

	}

	public String getParamName() {
		return mParamName;
	}

	public String toQuery() {
		if (getTable() == null) {
			return String.format("%s IN :%s", getColumn(), getParamName());
		}

		return String.format("%s.%s IN :%s", getTable(), getColumn(),
				getParamName());
	}
}
