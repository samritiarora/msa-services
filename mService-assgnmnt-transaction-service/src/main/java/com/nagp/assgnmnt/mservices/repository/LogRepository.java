package com.nagp.assgnmnt.mservices.repository;

import com.nagp.assgnmnt.mservices.model.LogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long>
{
    LogEntity findByUserId(UUID user);

    Page<LogEntity> findAllByUserIdAndDateCreatedGreaterThanEqualAndDateCreatedLessThanEqual(UUID userId,
                                                                                             LocalDateTime from,
                                                                                             LocalDateTime to,
                                                                                             Pageable pageable);
}
