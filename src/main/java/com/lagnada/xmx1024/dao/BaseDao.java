package com.lagnada.xmx1024.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public abstract class BaseDao {

    @PersistenceContext
    protected EntityManager entityManager;

    protected <C, A> CriteriaQuery<C> singleCriteria(Class<C> clazz, SingularAttribute<C, A> entityField,
                                                     A value, CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<C> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<C> from = criteriaQuery.from(clazz);
        criteriaQuery.where(criteriaBuilder.equal(from.get(entityField), value));
        return criteriaQuery;
    }

    protected <C, A> CriteriaQuery<C> singleCriteria(Class<C> clazz, SingularAttribute<C, A> entityField, A value) {
        return singleCriteria(clazz, entityField, value, entityManager.getCriteriaBuilder());
    }
}
