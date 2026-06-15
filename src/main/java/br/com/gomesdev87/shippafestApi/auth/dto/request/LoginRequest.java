package br.com.gomesdev87.shippafestApi.auth.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "O email é obrigatorio") String email,
        @NotEmpty(message = "A senha  é obrigatorio") String senha
) {
}
