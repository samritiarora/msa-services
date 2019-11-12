package com.nagp.assgnmnt.mservices.model;

import com.nagp.assgnmnt.mservices.model.base.AuditableEntity;

import java.io.Serializable;
import java.util.UUID;

@lombok.NoArgsConstructor
@lombok.Data
public class AccountEntity extends AuditableEntity implements Serializable
{
    private UUID accountId;

    private UUID userId;

    private int branch;

    private int noOfCqBkIssued;

    private String accountType;

    private double balance;

}
