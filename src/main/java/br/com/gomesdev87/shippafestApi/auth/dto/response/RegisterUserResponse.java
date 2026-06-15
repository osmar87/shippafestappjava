package br.com.gomesdev87.shippafestApi.auth.dto.response;

import br.com.gomesdev87.shippafestApi.user.User;

public record RegisterUserResponse(User user, String token) {
}
