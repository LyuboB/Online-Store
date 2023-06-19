package com.resellerapp.domain.entities;

import com.resellerapp.domain.enums.ConditionName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "conditions")
@Getter
@Setter
@NoArgsConstructor
public class Condition extends BaseEntity {

    @Column(name = "condition_name", unique = true, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ConditionName conditionName;
}
