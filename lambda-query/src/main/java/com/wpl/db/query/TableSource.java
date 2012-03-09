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
import java.util.HashMap;
import java.util.Map;

import com.wpl.db.query.utils.UniqueSequenceNumber;

public class TableSource implements ITableSource {

	private final ArrayList<String> mTables = new ArrayList<String>();
	private final Map<String, String> mAlias = new HashMap<String, String>();

	private final UniqueSequenceNumber mSeqNum = new UniqueSequenceNumber();

	public void addTable(String clazzName) {
		mTables.add(clazzName);
		String alias = "T" + mSeqNum.next();
		mAlias.put(clazzName, alias);
	}

	public String getAlias(String clazzName) {
		return mAlias.get(clazzName);
	}

	public String getNextParamName() {
		return "P" + mSeqNum.next();
	}

	@Override
	public String toString() {
		int index = 0;

		StringBuilder sb = new StringBuilder();

		for (String table : mTables) {
			if (index > 0) {
				sb.append(",");
			}

			sb.append(table).append(" ").append(getAlias(table));
		}

		return sb.toString();
	}
}
