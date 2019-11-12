package com.nagp.assgnmnt.mservices.model;

import com.nagp.assgnmnt.mServices.model.base.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_TX_LOG")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogEntity extends AuditableEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private UUID userId;

    private UUID accountId;

    private LocalDateTime timestamp;

    private String operation;

}
