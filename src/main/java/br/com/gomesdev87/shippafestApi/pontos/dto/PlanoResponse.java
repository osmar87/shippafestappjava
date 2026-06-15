package br.com.gomesdev87.shippafestApi.pontos.dto;
import br.com.gomesdev87.shippafestApi.pontos.Pontos;

public record PlanoResponse(java.util.Optional<Pontos> pontos, String userId) {}