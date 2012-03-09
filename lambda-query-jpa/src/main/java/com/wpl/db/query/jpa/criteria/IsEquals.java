package com.wpl.db.query.jpa.criteria;

import com.wpl.db.query.ITableSource;
import com.wpl.db.query.criteria.AbstractOneParamCriteria;

public class IsEquals<E> extends AbstractOneParamCriteria<E> {

	public IsEquals(final ITableSource tableSource, final String table,
			final String column, final E value) {
		super(tableSource, table, column, value, "=");
	}
}
