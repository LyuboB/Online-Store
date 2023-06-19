package com.resellerapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resellerapp.domain.entities.Condition;
import com.resellerapp.domain.enums.ConditionName;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {

    Optional<Condition> findByConditionName(ConditionName name);
}
