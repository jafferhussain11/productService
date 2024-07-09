package dev.jaffer.productService.clients.authenticationclient.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidateTokenResponseDto {

    private SessionStatus status;
    private UserDto user;
}
