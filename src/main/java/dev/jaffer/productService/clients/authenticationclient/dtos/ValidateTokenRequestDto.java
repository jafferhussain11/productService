package dev.jaffer.productService.clients.authenticationclient.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValidateTokenRequestDto {

    String token;
    Long userId;
}
