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

import junit.framework.Assert;

import org.junit.Test;

import com.wpl.db.query.IQueryBuilder;
import com.wpl.db.query.Person;
import com.wpl.db.query.jpa.Update;

public class UpdateQueryBuilderTest {

	@Test
	public void testUpdate() {

		IQueryBuilder query = new Update() {
			{
				update(Person.class);

				set(on(Person.class).getAge(), null);

				isNull(on(Person.class).getBirthday());
			}
		};

		System.out.println(query.toQuery());

		Assert.assertEquals(
				"UPDATE Person T0 SET T0.age=:age WHERE (T0.birthday IS NULL)",
				query.toQuery());
	}
}
