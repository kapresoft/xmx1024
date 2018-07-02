package com.lagnada.xmx1024.controller;

import com.lagnada.xmx1024.domain.Account;
import com.lagnada.xmx1024.representation.AccountRepresentation;
import com.lagnada.xmx1024.service.AccountRepository;
import com.lagnada.xmx1024.service.AccountService;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_ATOM_XML_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.HEAD;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "/api", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
public class AccountController {

    private final AccountService accountService;
    private final ConversionService conversionService;
    private AccountRepository accountRepository;

    @Autowired
    public AccountController(ConversionService conversionService, AccountService accountService,
                             AccountRepository accountRepository)
    {
        this.accountService = accountService;
        this.conversionService = conversionService;
        this.accountRepository = accountRepository;
    }

    @RequestMapping(value = "/account/{accountId}", method = {GET, HEAD})
    public ResponseEntity<AccountRepresentation> getAccount(@PathVariable Long accountId) {
        //Account account = accountService.getAccountById(accountId);
        Optional<Account> optionalAccount = ofNullable(accountRepository.findOne(accountId));
        //Optional<Account> optionalAccount = accountRepository.findById(accountId);
        Account account = optionalAccount.orElseThrow(() -> new EmptyResultDataAccessException("Account not found: " + accountId, 1));

        AccountRepresentation accountRepresentation = conversionService.convert(account, AccountRepresentation.class);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-My-Header", "Value");
        return new ResponseEntity<>(accountRepresentation, headers, OK);
    }

    @RequestMapping(value = "/accountFeed/{accountId}", method = {GET}, produces = APPLICATION_ATOM_XML_VALUE)
    public ResponseEntity<Feed> getAccountFeed(@PathVariable Long accountId) {

        AccountRepresentation accountRepresentation = new AccountRepresentation();
        accountRepresentation.setId(accountId);
        accountRepresentation.setFirstName("John");
        accountRepresentation.setLastName("Doe");

        UriComponents uri = ServletUriComponentsBuilder.fromCurrentRequest().build();
        accountRepresentation.setReference(uri.toUri());

        Feed feed = new Feed();
        feed.setFeedType("atom_1.0");
        feed.setTitle("AccountRepresentation Atom Feed");
        List<Entry> entries = new ArrayList<Entry>();
        Entry entry = new Entry();
        entry.setId(accountId.toString());
        entry.setTitle(accountRepresentation.getFullName());
        entry.setPublished(Calendar.getInstance().getTime());
        entries.add(entry);
        feed.setEntries(entries);
        return new ResponseEntity<Feed>(feed, OK);
    }

}
