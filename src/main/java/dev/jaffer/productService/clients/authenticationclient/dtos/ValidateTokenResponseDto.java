package dev.jaffer.productService.clients.authenticationclient.dtos;

import dev.jaffer.userservice.models.SessionStatus;
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
