package org.example.demo.domain.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;


public interface EntityBase<T extends Serializable> {
    public void setId(T id);
    public T getId();
}
