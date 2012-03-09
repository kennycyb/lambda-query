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
package com.wpl.db.query.jpa;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import com.wpl.db.query.AbstractQueryBuilder;
import com.wpl.db.query.IQuery;

/**
 * Limit the query result.
 */
public final class Limit extends AbstractQueryBuilder {

	private final Integer mFirst;
	private final Integer mMax;

	public Limit(final Integer first, final Integer max) {
		super(null);
		mFirst = first;
		mMax = max;
	}

	public void setParameter(final IQuery query) {

		final Query q = (Query) query.getQuery();

		if (mFirst != null) {
			q.setFirstResult(mFirst);
		}

		if (mMax != null) {
			q.setMaxResults(mMax);
		}
	}

	public String toQuery() {
		return StringUtils.EMPTY;
	}
}
