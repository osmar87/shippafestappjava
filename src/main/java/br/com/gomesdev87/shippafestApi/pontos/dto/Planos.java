package br.com.gomesdev87.shippafestApi.pontos.dto;

import java.time.LocalDate;

public record Planos(String plano) {

    public PlanoConfig planoOp() {

        return switch (plano) {
            case "vip" -> new PlanoConfig(plano, 50, 10, 5, false, new PlanoExpiracao().expiryDate(), LocalDate.now());
            case "pro" -> new PlanoConfig(plano, 30, 5, 3, true, new PlanoExpiracao().expiryDate(), LocalDate.now());
            default -> new PlanoConfig(plano, 10, 0, 0, false, new PlanoExpiracao().expiryDate(), LocalDate.now());
        };
    }

}
