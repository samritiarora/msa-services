package com.nagp.assgnmnt.mservices.controller;

import com.nagp.assgnmnt.mservices.model.CheckBookEntity;
import com.nagp.assgnmnt.mservices.model.UpdateStatusModel;
import com.nagp.assgnmnt.mservices.service.CheckBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
public class CheckBookController
{

    @Autowired
    private CheckBookService checkBookService;

    @GetMapping("/order/checkbook/{accountId}")
    public @ResponseBody
    ResponseEntity<Page<CheckBookEntity>> getCheckBookStatus(@PathVariable UUID accountId,
                                                       @RequestHeader Map<String, String> headers)
    {
        return ResponseEntity.ok(checkBookService.getOrdersByAccountId(accountId, headers));
    }

    @PostMapping("/order/checkbook")
    public @ResponseBody
    ResponseEntity<CheckBookEntity> postCheckBookOrder(@RequestBody CheckBookEntity checkBookEntity)
    {
        return ResponseEntity.ok(checkBookService.saveCheckBookEntity(checkBookEntity));
    }

    @PatchMapping("/order/checkbook/{accountId}")
    public @ResponseBody
    ResponseEntity<CheckBookEntity> postCheckBookOrder(@PathVariable UUID accountId, @RequestBody UpdateStatusModel
            updateStatusModel, @RequestHeader Map<String, String> headers)
    {
        return ResponseEntity.ok(checkBookService.updateStatus(accountId, updateStatusModel.getStatus(), headers));
    }
}
