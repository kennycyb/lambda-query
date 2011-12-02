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

import java.util.ArrayList;

import javax.persistence.Query;

public class Select extends QueryBuilder implements ISelectClause {

	private ArrayList<String> mTables = new ArrayList<String>();
	private OrderBy mOrder = new OrderBy();

	public void from(Class<?> clazz) {
		mTables.add(clazz.getSimpleName());
	}

	public void orderBy(Object argument) {

		String clazzName = className(argument);
		int classIndex = mTables.indexOf(clazzName);

		mOrder.orderBy(argument, "T" + classIndex);
	}

	public void orderByDesc(Object argument) {

		String clazzName = className(argument);
		int classIndex = mTables.indexOf(clazzName);

		mOrder.orderByDesc(argument, "T" + classIndex);
	}

	public void setParameter(Query query) {
		mOrder.setParameter(query);
	}

	public String toQuery() {

		StringBuilder sb = new StringBuilder();
		sb.append("FROM ");

		int index = 0;

		for (String table : mTables) {
			if (index > 0)
				sb.append(",");

			sb.append(table).append(" ").append("T").append(index++);
		}

		sb.append(mOrder.toQuery());

		return sb.toString();
	}
}
