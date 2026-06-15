package br.com.gomesdev87.shippafestApi.utils;
import java.time.MonthDay;

public record Signo(String nome, MonthDay inicio, MonthDay fim) {

    public boolean pertence(MonthDay dataNascimento) {
        // Trata o caso especial de signos que atravessam o ano (como Capricórnio)
        if (inicio.isAfter(fim)) {
            return !dataNascimento.isBefore(inicio) || !dataNascimento.isAfter(fim);
        }
        return !dataNascimento.isBefore(inicio) && !dataNascimento.isAfter(fim);
    }
}
