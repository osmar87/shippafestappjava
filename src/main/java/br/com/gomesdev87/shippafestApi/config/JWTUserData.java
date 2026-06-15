package br.com.gomesdev87.shippafestApi.config;
import lombok.Builder;


@Builder
public record JWTUserData(String userId, String email) {
}
