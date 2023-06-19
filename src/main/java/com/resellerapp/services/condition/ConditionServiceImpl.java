package com.resellerapp.services.condition;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resellerapp.domain.entities.Condition;
import com.resellerapp.domain.enums.ConditionName;
import com.resellerapp.dtos.condition.ConditionDTO;
import com.resellerapp.repository.ConditionRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ConditionServiceImpl implements ConditionService {

    private final ConditionRepository conditionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ConditionServiceImpl(ConditionRepository conditionRepository, ModelMapper modelMapper) {
        this.conditionRepository = conditionRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void postConstruct() {

        if (this.conditionRepository.count() == 0) {

            List<Condition> conditions = Arrays.stream(ConditionName.values())
                    .map(conditionName -> ConditionDTO.builder()
                            .conditionName(conditionName)
                            .build())
                    .map(condition -> this.modelMapper
                            .map(condition, Condition.class))
                    .toList();

            this.conditionRepository.saveAllAndFlush(conditions);
        }
    }

    public ConditionDTO findByName(ConditionName conditionName) {
        return this.modelMapper.map(this.conditionRepository.findByConditionName(conditionName).orElseThrow(),
                ConditionDTO.class);
    }
}
