package com.lagnada.xmx1024.controller;

import com.lagnada.xmx1024.domain.Account;
import com.lagnada.xmx1024.representation.AccountRepresentation;
import com.lagnada.xmx1024.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class AccountCollectionController extends BaseController {

    @Autowired
    private AccountService accountService;

    @ResponseBody
    @RequestMapping(value = "/accounts", method = POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<AccountRepresentation> createAccount(@RequestBody @Valid AccountRepresentation accountRepresentation) {
        Account account = conversionService.convert(accountRepresentation, Account.class);
        accountService.createAccount(account);

        AccountRepresentation createdAccountRepresentation = conversionService.convert(account, AccountRepresentation.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Location", createdAccountRepresentation.getReference().toASCIIString());
        ResponseEntity<AccountRepresentation> responseEntity = new ResponseEntity<AccountRepresentation>(createdAccountRepresentation, headers, HttpStatus.CREATED);
        return responseEntity;
    }

}
