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

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wpl.db.query.jpa.Select;

public class DerbyJdbcTest {

	static Connection sConnection;

	/**
	 * Check if table exist:
	 * 
	 * @throws Exception
	 */
	private static boolean checkTableExist(String name) throws Exception {
		String sql = String.format(
				"SELECT TABLENAME FROM SYS.SYSTABLES WHERE TABLENAME = '%s'",
				name);

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = DerbyJdbcTest.sConnection.createStatement();
			rs = stmt.executeQuery(sql);
			return rs.next();
		} finally {
			JdbcUtils.closeQuietly(rs);
			JdbcUtils.closeQuietly(stmt);
		}

	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		String currentPath = new File(".").getCanonicalPath();

		String connectionString = String.format(
				"jdbc:derby:%s/test;create=true", currentPath);

		DerbyJdbcTest.sConnection = DriverManager
				.getConnection(connectionString);

		if (!DerbyJdbcTest.checkTableExist("PERSON")) {
			String sql = "CREATE TABLE PERSON (ID BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, firstName VARCHAR(128), lastName VARCHAR(128))";

			Statement stmt = null;

			try {
				stmt = DerbyJdbcTest.sConnection.createStatement();
				stmt.execute(sql);
			} finally {
				JdbcUtils.closeQuietly(stmt);
			}
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DerbyJdbcTest.sConnection.close();
	}

	@Test
	public void test() {
		IQueryBuilder select = new Select() {
			{
				from(Person.class);
			}
		};

		System.out.println(select.toQuery());
	}

}
