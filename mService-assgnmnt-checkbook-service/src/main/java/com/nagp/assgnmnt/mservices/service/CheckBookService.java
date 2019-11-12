package com.nagp.assgnmnt.mservices.service;

import com.nagp.assgnmnt.mservices.model.CheckBookEntity;
import com.nagp.assgnmnt.mservices.model.StatusEnum;
import com.nagp.assgnmnt.mservices.repository.CheckBookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CheckBookService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckBookService.class);

    @Autowired
    private CheckBookRepository checkBookRepository;


    public Page<CheckBookEntity> getOrdersByAccountId(UUID accountId, Map<String, String> headers)
    {
        LOGGER.trace("Inside CheckBookService: get check book object for id {}", accountId);
        return checkBookRepository.findByAccountIdAndStatus(accountId, StatusEnum.ACTIVE, getPage(headers));
    }

    public CheckBookEntity saveCheckBookEntity(CheckBookEntity checkBookEntity)
    {
        LOGGER.trace("Inside CheckBookService: get check book object for id {}", checkBookEntity);
        //TODO: save only if not already present or could be checked at the calling service
        return checkBookRepository.save(checkBookEntity);
    }

    public CheckBookEntity updateStatus(UUID accountId, StatusEnum statusEnum, Map<String, String> headers)
    {
        LOGGER.trace("Inside CheckBookService: update status book object for id {}", statusEnum);
        Page<CheckBookEntity> optionalCheckBookEntity = checkBookRepository.findByAccountIdAndStatus(accountId,
                StatusEnum.ACTIVE, getPage(headers));
        CheckBookEntity checkBookEntity = optionalCheckBookEntity.get().findFirst().orElseThrow
                (NoSuchElementException::new);
        checkBookEntity.setStatus(statusEnum);
        return checkBookRepository.save(checkBookEntity);
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