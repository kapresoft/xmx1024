package com.lagnada.xmx1024.dao;

import com.lagnada.xmx1024.domain.Account;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.validation.constraints.NotNull;

public interface AccountDao {

    Account getAccountById(@NotNull Long accountId) throws EmptyResultDataAccessException;

    void createAccount(final Account account);

    Account getAccountByUsername(String username) throws EmptyResultDataAccessException;
}
