package com.nagp.assgnmnt.mServices.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@lombok.NoArgsConstructor
@lombok.Getter
@lombok.Setter
public abstract class AuditableEntity implements Serializable
{
    private static final long serialVersionUID = 8358693154477988813L;

    @CreatedBy
    @Column(name = "created_by")
    @JsonIgnore
    private String createdBy;

    @LastModifiedBy
    @Column(name = "modified_by")
    @JsonIgnore
    private String modifiedBy;

    @LastModifiedDate
    @Column(name = "date_modified")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateModified;

    @CreatedDate
    @Column(name = "date_created")
    @JsonIgnore
    private LocalDateTime dateCreated;
}
