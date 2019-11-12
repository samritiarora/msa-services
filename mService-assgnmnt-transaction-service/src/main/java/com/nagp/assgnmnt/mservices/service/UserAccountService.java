package com.nagp.assgnmnt.mservices.service;

import com.nagp.assgnmnt.mservices.exceptions.UnauthorizedException;
import com.nagp.assgnmnt.mservices.model.AccountEntity;
import com.nagp.assgnmnt.mservices.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class UserAccountService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    public AccountEntity saveAccount(String requestingUser, AccountEntity accountEntity)
    {
        UUID identity = new UUID(0, 0);
        //TODO: Move to api filter
        if (UUID.fromString(requestingUser).compareTo(identity) == 0)
        {
            LOGGER.trace("Inside UserAccountService: get user object for id {}", requestingUser);
        } else
        {
            throw new UnauthorizedException("No permsions to add account");
        }
        return accountRepository.save(accountEntity);
    }


    public Page<AccountEntity> getUserAccounts(UUID userEntity, Map<String, String> headers)
    {
        LOGGER.trace("Inside UserAccountService: get check book object for id {}", userEntity);
        return accountRepository.findByUserId(userEntity, getPage(headers));
    }

    /*public CheckBookEntity updateStatus(UUID accountId, StatusEnum statusEnum)
    {
        LOGGER.trace("Inside CheckBookService: update status book object for id {}", statusEnum);
        Optional<CheckBookEntity> optionalCheckBookEntity = accountRepository.findById(accountId);
        CheckBookEntity checkBookEntity = optionalCheckBookEntity.orElseThrow(() -> new NoSuchElementException
                ("NOT_FOUND"));
        checkBookEntity.setStatus(statusEnum);
        return accountRepository.save(checkBookEntity);
    }*/

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