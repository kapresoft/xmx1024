package com.lagnada.xmx1024.dao;

import com.lagnada.xmx1024.domain.Account;
import com.lagnada.xmx1024.domain.Account_;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.SingularAttribute;
import javax.validation.constraints.NotNull;

@Repository
public class AccountDaoImpl extends BaseDao implements AccountDao {

    @Override
    @Cacheable("account")
    public Account getAccountById(@NotNull Long accountId) throws EmptyResultDataAccessException {
        return entityManager
                .createQuery(singleCriteria(Account_.id, accountId))
                .getSingleResult();
    }

    @Override
    public void createAccount(Account account) {
        entityManager.persist(account);
    }

    @Override
    public Account getAccountByUsername(String username) throws EmptyResultDataAccessException {
        return entityManager
                .createQuery(singleCriteria(Account_.username, username))
                .getSingleResult();
    }

    private <T> CriteriaQuery<Account> singleCriteria(SingularAttribute<Account, T> accountField, T value) {
        return singleCriteria(Account.class, accountField, value);
    }

}
