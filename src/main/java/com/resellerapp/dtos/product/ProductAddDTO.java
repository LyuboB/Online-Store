package com.resellerapp.dtos.product;


import com.resellerapp.domain.enums.ConditionName;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class ProductAddDTO {

    @NotNull
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    @Size(min = 2, max = 50)
    private String description;

    @NotNull
    @Positive
    private Double price;

    @NotNull
    private ConditionName condition;
}
