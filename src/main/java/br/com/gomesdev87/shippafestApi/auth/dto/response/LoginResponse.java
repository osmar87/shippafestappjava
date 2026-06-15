package br.com.gomesdev87.shippafestApi.auth.dto.response;

public record LoginResponse(String token, UserResponse user) {
}
