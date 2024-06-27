package dev.jaffer.productService.clients.authenticationclient.dtos;

import dev.jaffer.userservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private String email;
    private Set<Role> roles = new HashSet<>();


}
