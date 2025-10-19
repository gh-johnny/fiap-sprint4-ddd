package com.dasa.keepinventory.infrastructure.persistence.entities;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
