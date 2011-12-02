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

import javax.persistence.Query;

public class Select extends QueryBuilder implements ISelectClause {

	private OrderBy mOrder;
	private Where mWhere;

	public Select() {
		super(new TableSource());
		mWhere = new Where(getTableSource());
		mOrder = new OrderBy(getTableSource());
	}

	// ~ Select Clause ---------------------------------------------------------

	public void from(Class<?> clazz) {
		getTableSource().addTable(clazz.getSimpleName());
	}

	// ~ Where Clause ----------------------------------------------------------

	public void isNull(Object argument) {
		mWhere.isNull(argument);
	}

	// ~ Order By Clause -------------------------------------------------------

	public void orderBy(Object argument) {
		mOrder.orderBy(argument);
	}

	public void orderByDesc(Object argument) {
		mOrder.orderByDesc(argument);
	}

	public void setParameter(Query query) {
		mOrder.setParameter(query);
	}

	public String toQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM ").append(getTableSource().toString());
		sb.append(mWhere.toQuery());
		sb.append(mOrder.toQuery());
		return sb.toString();
	}
}
