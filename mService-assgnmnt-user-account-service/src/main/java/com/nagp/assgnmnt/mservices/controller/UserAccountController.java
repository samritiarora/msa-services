package com.nagp.assgnmnt.mservices.controller;

import com.nagp.assgnmnt.mservices.model.AccountEntity;
import com.nagp.assgnmnt.mservices.model.UserEntity;
import com.nagp.assgnmnt.mservices.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/users")
public class UserAccountController
{
    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/{userId}")
    public @ResponseBody
    ResponseEntity<UserEntity> getUserInfo(@PathVariable UUID userId)
    {
        return ResponseEntity.ok(userAccountService.getUserById(userId));
    }

    @PostMapping("")
    public @ResponseBody
    ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity checkBookEntity)
    {
        return ResponseEntity.ok(userAccountService.saveUserEntity(checkBookEntity));
    }


    @PostMapping("/account")
    public @ResponseBody
    ResponseEntity<AccountEntity> postUserAccounts(@RequestBody AccountEntity userEntity, @RequestHeader Map<String,
            String> headers) throws URISyntaxException
    {
        return ResponseEntity.ok(userAccountService.saveAccount(headers.get("requesting-user"), userEntity));
    }
}
