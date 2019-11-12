package com.nagp.assgnmnt.mservices.controller;

import com.nagp.assgnmnt.mServices.model.TransferModel;
import com.nagp.assgnmnt.mservices.model.AccountEntity;
import com.nagp.assgnmnt.mservices.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/transaction")
public class TransactionController
{

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public @ResponseBody
    ResponseEntity<AccountEntity> transfer(@RequestBody TransferModel transferModel,
                                                       @RequestHeader Map<String, String> headers)
    {
        return ResponseEntity.ok(transactionService.transfer(transferModel, headers));
    }

    @PostMapping("/withdraw")
    public @ResponseBody
    ResponseEntity<AccountEntity> withdraw(@RequestBody TransferModel transferModel,
                                           @RequestHeader Map<String, String> headers)
    {
        return ResponseEntity.ok(transactionService.withdraw(transferModel, headers));
    }

    @PostMapping("/deposit")
    public @ResponseBody
    ResponseEntity<AccountEntity> deposit(@RequestBody TransferModel transferModel,
                                           @RequestHeader Map<String, String> headers)
    {
        return ResponseEntity.ok(transactionService.deposit(transferModel, headers));
    }

}
