package com.wpl.db.query.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.wpl.db.query.IQuery;
import com.wpl.db.query.IQueryBuilder;

public class JpaQuery implements IQuery {

	private final Query mQuery;

	public JpaQuery(final Query query) {
		mQuery = query;
	}

	public Object getQuery() {
		return mQuery;
	}

	public JpaQuery(final EntityManager em, final IQueryBuilder builder) {
		mQuery = em.createQuery(builder.toQuery());
	}

	public void setParameter(final String name, final Object value) {
		mQuery.setParameter(name, value);
	}

	public List<?> getResultList() {
		return mQuery.getResultList();
	}
}
