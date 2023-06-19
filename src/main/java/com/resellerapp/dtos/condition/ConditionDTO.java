package com.resellerapp.dtos.condition;

import com.resellerapp.domain.enums.ConditionName;
import com.resellerapp.dtos.BaseDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConditionDTO extends BaseDTO {

    @NotNull
    private ConditionName conditionName;
}
