package com.lagnada.xmx1024.loader;

import com.lagnada.xmx1024.domain.Account;
import com.lagnada.xmx1024.repository.AccountRepository;
import org.joda.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named("entityLoader")
public class EntityLoader {

    @Inject
    private AccountRepository accountRepository;

    @PostConstruct
    private void loadData() {
        Account account = new Account();
        account.setUsername("captain");
        account.setFirstName("Steve");
        account.setLastName("Rogers");
        account.setEmail("captain@america.com");
        account.setBirthdate(LocalDate.parse("1920-07-04").toDate());
        account.setDeleted(false);
        account.setPassword("password");
        accountRepository.save(account);
    }
}
