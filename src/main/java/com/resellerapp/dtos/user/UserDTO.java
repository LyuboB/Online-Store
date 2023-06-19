package com.resellerapp.dtos.user;

import java.util.Set;

import com.resellerapp.dtos.BaseDTO;
import com.resellerapp.dtos.product.ProductDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
public class UserDTO extends BaseDTO{

    // TODO: Can't get the id from the BaseDTO when this.userServiceImpl.findByEmail(userLoginDTO.getEmail()); (in LoginFormValidator in isValid())

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    @Size(min = 3, max = 20)
    private String password;

    @NotNull
    @Email
    private String email;

    private Set<ProductDTO> productsBought;

    private Set<ProductDTO> productsForSale;
}
