package com.nagp.assgnmnt.mservices.controller;

import com.nagp.assgnmnt.mServices.model.TransferModel;
import com.nagp.assgnmnt.mservices.model.AccountEntity;
import com.nagp.assgnmnt.mservices.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/accounts")
public class AccountsController
{

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/{userId}")
    public @ResponseBody
    ResponseEntity<Page<AccountEntity>> getUserAccounts(@PathVariable UUID accountId, @RequestHeader Map<String,
            String> headers)
    {
        return ResponseEntity.ok(userAccountService.getUserAccounts(accountId, headers));
    }

    @PostMapping("/user/accounts")
    public @ResponseBody
    ResponseEntity<AccountEntity> postUserAccounts(@RequestBody AccountEntity userEntity, @RequestHeader Map<String,
            String> headers)
    {
        return ResponseEntity.ok(userAccountService.saveAccount(headers.get("requesting-user"), userEntity));
    }

}
