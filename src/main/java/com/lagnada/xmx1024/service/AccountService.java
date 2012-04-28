package com.lagnada.xmx1024.service;

import com.lagnada.xmx1024.domain.Account;

public interface AccountService {

    Account getAccountById(Long accountId);

    void createAccount(Account account);

}
