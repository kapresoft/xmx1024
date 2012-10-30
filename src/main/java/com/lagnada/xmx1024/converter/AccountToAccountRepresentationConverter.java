package com.lagnada.xmx1024.converter;

import com.lagnada.xmx1024.domain.Account;
import com.lagnada.xmx1024.representation.AccountRepresentation;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;

@Component
public class AccountToAccountRepresentationConverter implements Converter<Account, AccountRepresentation> {

    @Override
    public AccountRepresentation convert(Account account) {
        AccountRepresentation representation = new AccountRepresentation();
        representation.setId(account.getId());
        representation.setUsername(account.getUsername());
        representation.setEmail(account.getEmail());
        representation.setFirstName(account.getFirstName());
        representation.setLastName(account.getLastName());
        LocalDate birthdate = null;
        if (account.getBirthdate() != null) {
            birthdate = LocalDate.fromDateFields(account.getBirthdate());
        }

        Date birthdayAgain = LocalDate.parse("1987-04-08", DateTimeFormat.forPattern("yyyy-MM-dd")).toDate();
        System.out.println(birthdayAgain);
        representation.setBirthdate(birthdate);
        representation.setPassword(null);
        representation.setDeleted(account.getDeleted() != null ? account.getDeleted() : Boolean.FALSE);
        // skip deletedByField

        URI reference = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/account")
                .path("/{id}")
                .buildAndExpand(account.getId())
                .toUri();
        representation.setReference(reference);
        return representation;
    }

}
