package com.wpl.db.query.jpa.criteria;

import java.util.Collection;

import com.wpl.db.query.IQuery;
import com.wpl.db.query.ITableSource;
import com.wpl.db.query.criteria.AbstractCriteriaCollection;
import com.wpl.db.query.criteria.ICriteria;

public class CriteriaCollection extends AbstractCriteriaCollection {

	protected CriteriaCollection(final String separator,
			final ITableSource tableSource) {
		super(separator, tableSource);
	}

	public void setParameter(final IQuery query) {
		for (final ICriteria c : getCriterias()) {
			c.setParameter(query);
		}
	}

	@Override
	protected ICriteria isEmpty(final ITableSource tableSource,
			final String table, final String column) {
		return new IsEmpty(tableSource, table, column);
	}

	@Override
	protected ICriteria isNull(final ITableSource tableSource,
			final String table, final String column) {
		return new IsNull(tableSource, table, column);
	}

	@Override
	protected ICriteria isNotNull(final ITableSource tableSource,
			final String table, final String column) {
		return new IsNotNull(tableSource, table, column);
	}

	@Override
	protected <E> ICriteria isEquals(final ITableSource tableSource,
			final String table, final String column, final E value) {
		return new IsEquals<E>(tableSource, table, column, value);
	}

	@Override
	protected <E> ICriteria isNotEquals(final ITableSource tableSource,
			final String table, final String column, final E value) {
		return new IsNotEquals<E>(tableSource, table, column, value);
	}

	@Override
	protected <E> ICriteria between(final ITableSource tableSource,
			final String table, final String column, final E min, final E max) {
		return new Between<E>(tableSource, table, column, min, max);
	}

	@Override
	protected ICriteria notLike(final ITableSource tableSource,
			final String table, final String column, final String pattern) {
		return new NotLike(tableSource, table, column, pattern);
	}

	@Override
	protected ICriteria isNotEmpty(final ITableSource tableSource,
			final String table, final String column) {
		return new IsNotEmpty(tableSource, table, column);
	}

	@Override
	protected <E> ICriteria greaterThan(final ITableSource tableSource,
			final String table, final String column, final E value) {
		return new GreaterThan<E>(tableSource, table, column, value);
	}

	@Override
	protected <E> ICriteria lessThan(final ITableSource tableSource,
			final String table, final String column, final E value) {
		return new LessThan<E>(tableSource, table, column, value);
	}

	@Override
	protected ICriteria like(final ITableSource tableSource,
			final String table, final String column, final String pattern) {
		return new Like(tableSource, table, column, pattern);
	};

	@Override
	protected <E> ICriteria in(final ITableSource tableSource,
			final String table, final String column, final Collection<E> values) {
		return new In(tableSource, table, column, values);
	}
}
