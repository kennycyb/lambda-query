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

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.wpl.db.query.jpa.Select;

public class SelectQueryBuilderTest {

	@Test
	public void testSelect() {
		IQueryBuilder query = new Select() {
			{
				from(Person.class);

				orderBy(on(Person.class).getLastName());
			}
		};

		Assert.assertEquals("FROM Person T0 ORDER BY T0.lastName",
				query.toQuery());
	}

	@Test
	public void testWhereIsNull() {
		IQueryBuilder query = new Select() {
			{
				from(Person.class);

				isNull(on(Person.class).getBirthday());
			}
		};

		Assert.assertEquals("FROM Person T0 WHERE (T0.birthday IS NULL)",
				query.toQuery());
	}

	@Test
	public void testWhereIsEquals() {

		IQueryBuilder query = new Select() {
			{
				from(Person.class);

				isEquals(on(Person.class).getFirstName(), "Kenny");
				isEquals(on(Person.class).getLastName(), "Chong");
			}
		};

		System.out.println(query.toQuery());

		Assert.assertEquals(
				"FROM Person T0 WHERE (T0.firstName=:P1 AND T0.lastName=:P2)",
				query.toQuery());

	}

	@Test
	public void testWhereIsEmpty() {

		IQueryBuilder query = new Select() {
			{
				from(Person.class);

				isEmpty(on(Person.class).getContacts());
			}
		};

		System.out.println(query.toQuery());

		Assert.assertEquals("FROM Person T0 WHERE (T0.contacts IS EMPTY)",
				query.toQuery());

	}

	@Test
	public void testWhereBetween() {

		IQueryBuilder query = new Select() {
			{
				from(Person.class);

				between(on(Person.class).getAge(), 18, 55);
			}
		};

		System.out.println(query.toQuery());

		Assert.assertEquals(
				"FROM Person T0 WHERE (T0.age BETWEEN :P1 AND :P2)",
				query.toQuery());

	}

	@Ignore
	@Test
	public void testWhereOr() {

		IQueryBuilder query = new Select() {
			{
				from(Person.class);
				/*
				 * 
				 * or().isEquals(on(Person.class).getFirstName(), "Kenny")
				 * .isEquals(on(Person.class).getLastName(), "Chong");
				 */
			}
		};

		System.out.println(query.toQuery());

		Assert.assertEquals(
				"FROM Person T0 WHERE (T0.firstName=:P1 OR T0.lastName=:P2)",
				query.toQuery());

	}

	@Test
	public void testAll() {
		IQueryBuilder query = new Select() {
			{
				from(Person.class);

				isNull(on(Person.class).getBirthday());

				orderBy(on(Person.class).getLastName());

				limit(null, 1);
			}
		};

		Assert.assertEquals(
				"FROM Person T0 WHERE (T0.birthday IS NULL) ORDER BY T0.lastName",
				query.toQuery());

	}
}
