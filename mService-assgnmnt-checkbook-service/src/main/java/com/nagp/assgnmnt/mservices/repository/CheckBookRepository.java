package com.nagp.assgnmnt.mservices.repository;

import com.nagp.assgnmnt.mservices.model.CheckBookEntity;
import com.nagp.assgnmnt.mservices.model.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CheckBookRepository extends JpaRepository<CheckBookEntity, Integer>
{
    Page<CheckBookEntity> findByAccountIdAndStatus(UUID accountId, StatusEnum status, Pageable pageable);
    //TODO: Check book status to be maintained from tb_account and join with tb_chbk_status
}
