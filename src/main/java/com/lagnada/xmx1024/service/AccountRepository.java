package com.lagnada.xmx1024.service;

import com.lagnada.xmx1024.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findById(Account account);

}
