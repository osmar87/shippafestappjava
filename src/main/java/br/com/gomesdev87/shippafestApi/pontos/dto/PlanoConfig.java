package br.com.gomesdev87.shippafestApi.pontos.dto;

import java.time.LocalDate;

public record PlanoConfig (
        String plano,
        int likes,
        int superLikes,
        int voltars,
        boolean myLikes,
        LocalDate expiryDate,
        LocalDate dateUpdate
){
}
