package com.wpl.db.query.jpa.criteria;

import com.wpl.db.query.ITableSource;
import com.wpl.db.query.criteria.AbstractZeroParamCriteria;

public class IsEmpty extends AbstractZeroParamCriteria {

	public IsEmpty(final ITableSource tableSource, final String table,
			final String column) {
		super(tableSource, table, column);
	}

	public String toQuery() {
		if (getTable() == null) {
			return String.format("%s IS EMPTY", getColumn());
		}

		return String.format("%s.%s IS EMPTY", getTable(), getColumn());
	}
}
