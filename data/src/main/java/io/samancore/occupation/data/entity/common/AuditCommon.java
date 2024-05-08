package io.samancore.occupation.data.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import io.samancore.occupation.model.type.GeneralStatus;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class AuditCommon {

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(length = 64, nullable = false)
    private String createBy;

    @Column(length = 64, nullable = false)
    private String updateBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updateAt;

    @Column(nullable = false)
    private GeneralStatus generalStatus;

    @Column(length = 16, nullable = false)
    private String createIpAddress;

    @Column(length = 16, nullable = false)
    private String updateIpAddress;
}
