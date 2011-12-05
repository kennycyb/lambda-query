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

import javax.persistence.Query;

import com.wpl.db.query.ITableSource;

public class NotLike extends Criteria {
	private final String mPattern;
	private final String mParamName;

	public NotLike(ITableSource tableSource, String table, String column,
			String pattern) {
		super(tableSource, table, column);
		this.mPattern = pattern;
		this.mParamName = getNextParamName();
	}

	public String getPattern() {
		return mPattern;
	}

	public String getParamName() {
		return mParamName;
	}

	public void setParameter(Query query) {
		query.setParameter(getParamName(), getPattern());
	}

	public String toQuery() {
		if (getTable() == null) {
			return String
					.format("%s NOT LIKE :%s", getColumn(), getParamName());
		}

		return String.format("%s.%s NOT LIKE :%s", getTable(), getColumn(),
				getParamName());
	}
}
