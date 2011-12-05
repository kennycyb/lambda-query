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
package com.wpl.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.wpl.db.query.IQueryBuilder;
import com.wpl.db.query.Select;

public class Dao {

	private final EntityManager mEntityManager;

	public Dao(EntityManager entityManager) {
		this.mEntityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return mEntityManager;
	}

	public <E> E firstOrDefault(final Class<E> clazz) {
		IQueryBuilder sql = new Select() {
			{
				from(clazz);

				limit(null, 1);
			}
		};

		Query query = getEntityManager().createQuery(sql.toQuery());
		sql.setParameter(query);

		@SuppressWarnings("unchecked")
		List<E> results = query.getResultList();

		if (results == null || results.size() == 0) {
			return null;
		}

		return clazz.cast(results.get(0));
	}
}
