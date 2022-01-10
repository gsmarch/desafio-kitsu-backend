package com.challenge.kitsuchallenge.model;

import java.time.LocalDateTime;

/* @MappedSuperClass */
public abstract class BaseEntity {
    /* @Column(updatable = false) */
    /* @CreationTimestamp */
    private LocalDateTime createdAt;
    
    /* @Column(updatable = false) */
    /* @UpdateTimestamp */
    private LocalDateTime updatedAt;
}
