package com.nagp.assgnmnt.mservices.model;

import com.nagp.assgnmnt.mservices.model.base.AuditableEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_CHECKBOOK_ORDER")//TODO: Remove status table from flyway config
@lombok.NoArgsConstructor
@lombok.Data
public class CheckBookEntity extends AuditableEntity implements Serializable
{
    @Id
    @Column(name = "id")
    private Integer checkbookRequestId;

    @NotEmpty(message = "is a required property")
    @Pattern(regexp = "^(?!\\s*$).+", message = "must not be empty")
    @Column(name = "user_id")
    private UUID userId;

    @NotEmpty(message = "is a required property")
    @Pattern(regexp = "^(?!\\s*$).+", message = "must not be empty")
    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "ch_bk_status")
    private StatusEnum status = StatusEnum.ACTIVE;

}
