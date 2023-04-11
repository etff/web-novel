package com.example.webnovel.global.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
//
//    @CreatedBy
//    @Column(updatable = false, nullable = false)
//    private String createdBy;
@CreatedDate
private String createTime;

    @LastModifiedDate
    private String updateTime;

//    @LastModifiedBy
//    @Column(nullable = false)
//    private String modifiedBy;

    @PrePersist
    public void onPrePersist() {
        this.createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }
}
