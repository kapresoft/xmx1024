package com.lagnada.xmx1024.controller;

import com.lagnada.xmx1024.domain.Account;
import com.lagnada.xmx1024.representation.AccountRepresentation;
import com.lagnada.xmx1024.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api",
        consumes = APPLICATION_JSON_VALUE, produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
public class AccountCollectionController {

    private final ConversionService conversionService;
    private final AccountService accountService;

    @Autowired
    public AccountCollectionController(ConversionService conversionService, AccountService accountService)
    {
        this.conversionService = conversionService;
        this.accountService = accountService;
    }

    @RequestMapping(value = "/accounts", method = POST)
    public ResponseEntity<AccountRepresentation> createAccount(@RequestBody @Valid AccountRepresentation accountRepresentation)
    {
        Account account = conversionService.convert(accountRepresentation, Account.class);
        accountService.createAccount(account);

        AccountRepresentation createdAccountRepresentation = conversionService.convert(account, AccountRepresentation.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Location", createdAccountRepresentation.getReference().toASCIIString());
        ResponseEntity<AccountRepresentation> responseEntity = new ResponseEntity<AccountRepresentation>(createdAccountRepresentation, headers, HttpStatus.CREATED);
        return responseEntity;
    }

}
