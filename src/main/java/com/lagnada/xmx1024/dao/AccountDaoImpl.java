package com.lagnada.xmx1024.dao;

import com.lagnada.xmx1024.domain.Account;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public class AccountDaoImpl extends BaseDao implements AccountDao {

    @Override
    @Cacheable("account")
    public Account getAccountById(@NotNull Long accountId) throws EmptyResultDataAccessException {
        return entityManager
                .createQuery(singleCriteria(Account.class, "id", accountId))
                .getSingleResult();
    }

    @Override
    public void createAccount(Account account) {
        entityManager.persist(account);
    }

    @Override
    public Account getAccountByUsername(String username) throws EmptyResultDataAccessException {
        return entityManager
                .createQuery(singleCriteria(Account.class, "username", username))
                .getSingleResult();
    }

}
