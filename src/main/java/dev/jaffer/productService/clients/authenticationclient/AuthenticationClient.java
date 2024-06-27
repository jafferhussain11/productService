package dev.jaffer.productService.clients.authenticationclient;

import dev.jaffer.productService.clients.authenticationclient.dtos.ValidateTokenRequestDto;
import dev.jaffer.productService.clients.authenticationclient.dtos.ValidateTokenResponseDto;
import dev.jaffer.productService.clients.fakeStoreApi.FakeStoreProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class AuthenticationClient {
    private RestTemplateBuilder restTemplateBuilder;

    public AuthenticationClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public ValidateTokenResponseDto validateToken(String token, Long userId){
        RestTemplate restTemplate = restTemplateBuilder.build();

        ValidateTokenRequestDto request = new ValidateTokenRequestDto();
        request.setToken(token);
        request.setUserId(userId);

        ResponseEntity<ValidateTokenResponseDto> response = restTemplate.postForEntity("http://localhost:9000/validateSession",
                request,
                ValidateTokenResponseDto.class);
        return response.getBody();
    }

//    public void getUser(String token){
//        return null;
//    }
}
