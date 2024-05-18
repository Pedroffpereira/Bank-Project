package com.example.Bank.Entaties;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

/**
 * Abstract class representing common fields for entities.
 * 
 * This class provides a unique identifier (UUID) and timestamps
 * for record creation and updates. It is intended to be extended
 * by other entity classes to inherit these common fields.
 * 
 * @author Pedro Pereira
 */
@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AbstractEntaties {
    /**
     * Unique identifier for the entity.
     * 
     * This field is generated automatically using a UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Timestamp indicating when the record was created.
     * 
     * This field is automatically populated when the record is created
     * and is not updatable.
     */
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * Timestamp indicating when the record was last updated.
     * 
     * This field is automatically populated with the current timestamp
     * whenever the record is updated.
     */
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
