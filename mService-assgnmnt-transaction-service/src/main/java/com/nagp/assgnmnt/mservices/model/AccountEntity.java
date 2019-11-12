package com.nagp.assgnmnt.mservices.model;

import com.nagp.assgnmnt.mServices.model.base.AuditableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_ACCOUNT")//TODO: Remove status table from flyway config
@lombok.NoArgsConstructor
@lombok.Data
public class AccountEntity extends AuditableEntity implements Serializable
{
    @Id
    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "user_id")
    private UUID userId;

    @NotEmpty(message = "is a required property")
    @Column(name = "branch_id")
    private int branch;

    @Column(name = "cheque_bk_issued")
    private int noOfCqBkIssued;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "account_balance")
    private double balance;

}
