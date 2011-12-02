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

public class IsEquals extends Criteria {

	private final String mParamName;
	private final Object mValue;

	public IsEquals(String table, String column, Object value) {
		super(table, column);

		mParamName = String.format("P%d", sSeqNumber.next());
		mValue = value;
	}

	public String getParamName() {
		return mParamName;
	}

	public Object getValue() {
		return mValue;
	}

	public String toQuery() {

		if (getTable() == null) {
			return String.format("%s=:%s", getColumn(), getParamName());
		}

		return String.format("%s.%s=:%s", getTable(), getColumn(),
				getParamName());
	}

	public void setParameter(Query query) {
		query.setParameter(getParamName(), getValue());
	}
}
