package com.lagnada.xmx1024.controller;

import com.lagnada.xmx1024.domain.Account;
import com.lagnada.xmx1024.representation.AccountRepresentation;
import com.lagnada.xmx1024.service.AccountService;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.HEAD;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(produces = {"application/json"})
public class AccountController extends BaseController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ConversionService conversionService;

    @RequestMapping(value = "/account/{accountId}", method = {GET, HEAD}, produces = {"application/json", "application/atom+xml"})
    @ResponseBody
    @MyAnnotation
    public ResponseEntity<AccountRepresentation> getAccount(@PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId);
        AccountRepresentation accountRepresentation = conversionService.convert(account, AccountRepresentation.class);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-My-Header", "Value");

        UriComponents uri = ServletUriComponentsBuilder.fromCurrentRequest().build();
        accountRepresentation.setReference(uri.toUri());
        return new ResponseEntity<AccountRepresentation>(accountRepresentation, headers, OK);
    }

    @RequestMapping(value = "/accountFeed/{accountId}", method = {GET}, headers = "Accept=application/atom+xml", produces = "application/atom+xml")
    @ResponseBody
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
