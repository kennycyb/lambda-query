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

import com.wpl.db.query.ITableSource;
import com.wpl.db.query.LambdaBase;

public abstract class Criteria extends LambdaBase implements ICriteria {

	private final String mColumn;
	private final String mTable;
	private final ITableSource mTableSource;

	public Criteria(ITableSource tableSource, String table, String column) {
		this.mTable = table;
		this.mColumn = column;
		this.mTableSource = tableSource;
	}

	protected final String getNextParamName() {
		return mTableSource.getNextParamName();
	}

	public final String getColumn() {
		return mColumn;
	}

	public final String getTable() {
		return mTable;
	}

	@Override
	public final String toString() {
		return toQuery();
	}
}
