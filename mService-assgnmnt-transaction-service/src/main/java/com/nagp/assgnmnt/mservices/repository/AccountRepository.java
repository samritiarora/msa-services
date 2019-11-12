package com.nagp.assgnmnt.mservices.repository;

import com.nagp.assgnmnt.mservices.model.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID>
{
    Page<AccountEntity> findByUserId(UUID userEntity, Pageable pageable);
    //TODO: Check book status to be maintained from tb_account and join with tb_chbk_status
}
