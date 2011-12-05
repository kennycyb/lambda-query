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

public class IsEmpty extends Criteria {

	public IsEmpty(String table, String column) {
		super(table, column);
	}

	public void setParameter(Query query) {
		// DO NOTHING
	}

	public String toQuery() {
		if (getTable() == null)
			return String.format("%s IS EMPTY", getColumn());

		return String.format("%s.%s IS EMPTY", getTable(), getColumn());
	}
}
