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

import com.wpl.db.query.ITableSource;
import com.wpl.db.query.criteria.AbstractZeroParamCriteria;

public final class IsNotEmpty extends AbstractZeroParamCriteria {

	public IsNotEmpty(final ITableSource tableSource, final String table,
			final String column) {
		super(tableSource, table, column);
	}

	public final String toQuery() {
		if (getTable() == null) {
			return String.format("%s IS NOT EMPTY", getColumn());
		}

		return String.format("%s.%s IS NOT EMPTY", getTable(), getColumn());
	}
}
