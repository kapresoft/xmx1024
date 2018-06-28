package com.lagnada.xmx1024.converter;

import com.lagnada.xmx1024.domain.Account;
import com.lagnada.xmx1024.representation.AccountRepresentation;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AccountRepresentationToAccountConverter implements Converter<AccountRepresentation, Account> {

    @Override
    public Account convert(AccountRepresentation representation) {
        Account account = null;
        if (representation != null) {
            account = new Account();
            account.setId(representation.getId());
            account.setUsername(representation.getUsername());
            account.setEmail(representation.getEmail());
            account.setFirstName(representation.getFirstName());
            account.setLastName(representation.getLastName());
            LocalDate birthdate = representation.getBirthdate();
            account.setBirthdate(birthdate != null ? birthdate.toDate() : null);
            account.setPassword(representation.getPassword());
            account.setDeleted(representation.isDeleted());
            account.setDeletedBy(null);
            // skip reference field
        }
        return account;
    }
}
