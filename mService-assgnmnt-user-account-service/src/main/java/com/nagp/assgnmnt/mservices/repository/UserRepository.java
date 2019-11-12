package com.nagp.assgnmnt.mservices.repository;

import com.nagp.assgnmnt.mservices.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>
{
    //TODO: Check book status to be maintained from tb_account and join with tb_chbk_status
}
