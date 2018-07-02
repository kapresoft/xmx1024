package com.lagnada.xmx1024.service;

import com.lagnada.xmx1024.dao.AccountDao;
import com.lagnada.xmx1024.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account getAccountById(Long accountId) {
        checkArgument(accountId != null, "AccountId arg was null");
        return accountDao.getAccountById(accountId);
    }

    @Override
    @Transactional
    public void createAccount(final Account account) {
        checkArgument(account != null, "Account arg was null");
        checkExistingAccount(account.getUsername());
        accountDao.createAccount(account);
    }

    private void checkExistingAccount(@NotNull String username) {
        try {
            accountDao.getAccountByUsername(username);
            throw new DataIntegrityViolationException("Account with username exists: " + username);
        } catch (EmptyResultDataAccessException e) { /* no op */ }
    }
}
