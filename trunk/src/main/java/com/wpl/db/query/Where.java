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

import java.util.Collection;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import com.wpl.db.query.criteria.And;

public class Where extends QueryBuilder implements IWhereClause {

	private final And mAnd;

	public Where(ITableSource tableSource) {
		super(tableSource);
		mAnd = new And(tableSource);
	}

	// ~ Implementation of IWhereClause ----------------------------------------

	public void isNull(Object argument) {
		mAnd.isNull(argument);
	}

	public <E> void isEquals(E argument, E value) {
		mAnd.isEquals(argument, value);
	}

	public void like(Object argument, String pattern) {
		mAnd.like(argument, pattern);
	}

	public <E> void between(E argument, E min, E max) {
		mAnd.between(argument, min, max);
	}

	public void isEmpty(Collection<?> argument) {
		mAnd.isEmpty(argument);
	}

	public <E> void greaterThan(E argument, E value) {
		mAnd.greaterThan(argument, value);
	}

	public <E> void lessThan(E argument, E value) {
		mAnd.lessThan(argument, value);
	}

	// ~ Implementation of IQueryBuilder ---------------------------------------

	public void setParameter(Query query) {
		mAnd.setParameter(query);
	}

	public String toQuery() {
		return mAnd.count() == 0 ? StringUtils.EMPTY : String.format(
				" WHERE %s", mAnd.toQuery());
	}
}
