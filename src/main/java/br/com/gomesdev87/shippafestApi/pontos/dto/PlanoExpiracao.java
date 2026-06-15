package br.com.gomesdev87.shippafestApi.pontos.dto;

import java.time.LocalDate;

public record PlanoExpiracao(LocalDate expiryDate) {

    public PlanoExpiracao() {
        this(LocalDate.now().plusDays(32));
    }
}
