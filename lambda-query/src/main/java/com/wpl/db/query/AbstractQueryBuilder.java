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

public abstract class AbstractQueryBuilder extends LambdaBase implements IQueryBuilder {

	private final ITableSource mTableSource;

	protected AbstractQueryBuilder(ITableSource tableSource) {
		mTableSource = tableSource == null ? new TableSource() : tableSource;
	}

	@Override
	public String toString() {
		return toQuery();
	}

	protected ITableSource getTableSource() {
		return mTableSource;
	}

	protected String getTableAlias(Object argument) {
		if (mTableSource == null) {
			return null;
		}

		return mTableSource.getAlias(className(argument));
	}
}
