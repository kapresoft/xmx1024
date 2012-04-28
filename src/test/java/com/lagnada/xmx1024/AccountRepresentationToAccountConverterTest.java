package com.lagnada.xmx1024;

import com.lagnada.xmx1024.domain.Account;
import com.lagnada.xmx1024.representation.AccountRepresentation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = {"classpath:app-ctx.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountRepresentationToAccountConverterTest {

    @Autowired
    ConversionService conversionService;

    @Test
    public void convertOne() {
        AccountRepresentation accountRepresentation = new AccountRepresentation();
        accountRepresentation.setId(1000L);

        Account account = conversionService.convert(accountRepresentation, Account.class);
        assertThat(account, is(notNullValue()));
        assertThat(account.getId(), is(1000L));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertMany() {
        List<AccountRepresentation> accountRepresentationList = new ArrayList<AccountRepresentation>();
        AccountRepresentation accountRepresentation1 = new AccountRepresentation();
        accountRepresentation1.setId(1000L);
        AccountRepresentation accountRepresentation2 = new AccountRepresentation();
        accountRepresentation2.setId(1001L);
        accountRepresentationList.add(accountRepresentation1);
        accountRepresentationList.add(accountRepresentation2);

        TypeDescriptor sourceType = TypeDescriptor.forObject(accountRepresentationList);
        TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Account.class));
        List<Account> converted = (List<Account>) conversionService.convert(accountRepresentationList, sourceType, targetType);
        assertThat(converted, is(notNullValue()));
        assertThat(converted.size(), is(2));
        assertThat(converted.get(0).getId(), is(1000L));
        assertThat(converted.get(1).getId(), is(1001L));
    }

}
