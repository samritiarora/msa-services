package com.nagp.assgnmnt.mservices.service;

import com.nagp.assgnmnt.mServices.model.TransferModel;
import com.nagp.assgnmnt.mservices.model.AccountEntity;
import com.nagp.assgnmnt.mservices.model.LogEntity;
import com.nagp.assgnmnt.mservices.repository.AccountRepository;
import com.nagp.assgnmnt.mservices.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class TransactionService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private AccountRepository accountRepository;

    public AccountEntity withdraw(TransferModel transferModel, Map<String, String> requestHeaders)
    {
        Double amount = transferModel.getAmount();
        AccountEntity from = accountRepository.findById(transferModel.getFrom()).orElseThrow(IllegalArgumentException::new);
        if ((from.getBalance() > amount && amount > 0))
        {
            from.setBalance(from.getBalance() - amount);
            accountRepository.save(from);
        } else
        {
            throw new ValidationException("Insufficient Balance");
        }
        LogEntity logEntity = LogEntity.builder()
                .operation("Withdraw From: " + from.getAccountId() + " Amount: " + amount)
                .timestamp(LocalDateTime.now())
                .userId(UUID.fromString(requestHeaders.get("userId"))).build();

        logRepository.save(logEntity);
        return from;
    }

    public AccountEntity deposit(TransferModel transferModel, Map<String, String> requestHeaders)
    {
        Double amount = transferModel.getAmount();
        AccountEntity from = accountRepository.findById(transferModel.getFrom()).orElseThrow(IllegalArgumentException::new);
            from.setBalance(from.getBalance() + amount);
            accountRepository.save(from);
        LogEntity logEntity = LogEntity.builder()
                .operation("Deposit to: " + from.getAccountId() + " Amount: " + amount)
                .timestamp(LocalDateTime.now())
                .userId(UUID.fromString(requestHeaders.get("userId"))).build();

        logRepository.save(logEntity);
        return from;
    }


        public AccountEntity transfer(TransferModel transferModel, Map<String, String> requestHeaders)
    {
        Double amount = transferModel.getAmount();
        AccountEntity from = accountRepository.findById(transferModel.getFrom()).orElseThrow
                (IllegalArgumentException::new);
        AccountEntity to = accountRepository.findById(transferModel.getTo()).orElseThrow
                (IllegalArgumentException::new);
        if ((from.getBalance() > amount && amount > 0))
        {
            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
            accountRepository.save(from);
            accountRepository.save(to);
        } else
        {
            throw new ValidationException("Insufficient Balance");
        }

        LogEntity logEntity = LogEntity.builder()
                .operation("Transferred: From: " + from.getAccountId() + " To: " + to.getAccountId()
                 + " Amount: " + amount)
        .timestamp(LocalDateTime.now())
                .userId(UUID.fromString(requestHeaders.get("userId"))).build();

        logRepository.save(logEntity);
        return from;
    }

    public void getLogsForId(UUID userId, LocalDateTime from, LocalDateTime to, Map<String, String> requestHeaders)
    {
        logRepository.findAllByUserIdAndDateCreatedGreaterThanEqualAndDateCreatedLessThanEqual(userId, from, to,
                getPage(requestHeaders));
    }

    private Pageable getPage(Map<String, String> headers)
    {
        int page = 0;
        int size = 50;
        Sort.Direction sortOrder = Sort.Direction.DESC;

        if (headers.containsKey("page"))
        {
            page = Integer.parseInt(headers.get("page"));
        }
        if (headers.containsKey("size"))
        {
            size = Integer.parseInt(headers.get("size"));
        }
        return PageRequest.of(page, size, Sort.by("dateModified").descending());
    }
}