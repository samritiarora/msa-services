package com.nagp.assgnmnt.mservices.model;

import com.nagp.assgnmnt.mservices.model.base.AuditableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_USER_ENTITY")
@lombok.NoArgsConstructor
@lombok.Data
public class UserEntity extends AuditableEntity implements Serializable
{

    @Id
    @NotEmpty(message = "is a required property")
    @Column(name = "user_id")
    private UUID userId;

    @Column(unique = true)
    private String username;

    @NotEmpty(message = "is a required property")
    private String password;

    @Transient
    private List<AccountEntity> accounts;
}
