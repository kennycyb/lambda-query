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
package com.wpl.db.query.impl;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

/**
 * Limit the query result.
 */
public final class Limit extends QueryBuilder {

	private final Integer mFirst;
	private final Integer mMax;

	public Limit(Integer first, Integer max) {
		super(null);
		this.mFirst = first;
		this.mMax = max;
	}

	public void setParameter(Query query) {
		if (this.mFirst != null) {
			query.setFirstResult(this.mFirst);
		}

		if (this.mMax != null) {
			query.setMaxResults(this.mMax);
		}
	}

	public String toQuery() {
		return StringUtils.EMPTY;
	}
}
