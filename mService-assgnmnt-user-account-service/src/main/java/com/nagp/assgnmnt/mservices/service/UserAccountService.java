package com.nagp.assgnmnt.mservices.service;

import com.nagp.assgnmnt.mservices.exceptions.UnauthorizedException;
import com.nagp.assgnmnt.mservices.model.AccountEntity;
import com.nagp.assgnmnt.mservices.model.UserEntity;
import com.nagp.assgnmnt.mservices.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserAccountService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public UserEntity getUserById(UUID userId)
    {
        LOGGER.trace("Inside UserAccountService: get user object for id {}", userId);
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        optionalUserEntity.orElseThrow(() -> new NoSuchElementException("NOT_FOUND"));
        ResponseEntity<Page<AccountEntity>> accountEntityPage = restTemplate.exchange
                ("https://msa-transaction-service/api/accounts/" + userId, HttpMethod.GET, null, new
                        ParameterizedTypeReference<Page<AccountEntity>>()
        {
        });

        UserEntity userEntity = optionalUserEntity.get();
        userEntity.setAccounts(accountEntityPage.getBody().getContent());
        return userEntity;
    }


    public UserEntity saveUserEntity(UserEntity userEntity)
    {
        LOGGER.trace("Inside UserAccountService: get check book object for id {}", userEntity);
        return userRepository.save(userEntity);
    }

    public AccountEntity saveAccount(String requestingUser, AccountEntity accountEntity) throws URISyntaxException
    {
        UUID identity = new UUID(0, 0);
        //TODO: Move to api filter
        if (UUID.fromString(requestingUser).compareTo(identity) == 0)
        {
            LOGGER.trace("Inside UserAccountService: get user object for id {}", requestingUser);
        } else
        {
            throw new UnauthorizedException("No permissions to add account");
        }
        ResponseEntity<AccountEntity> accountEntityPage = restTemplate.postForEntity
                (new URI("https://msa-transaction-service/api/accounts"),  accountEntity, AccountEntity.class);
        return accountEntityPage.getBody();
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