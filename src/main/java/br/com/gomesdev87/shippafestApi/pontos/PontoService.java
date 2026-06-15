package br.com.gomesdev87.shippafestApi.pontos;

import br.com.gomesdev87.shippafestApi.pontos.dto.PlanoConfig;
import br.com.gomesdev87.shippafestApi.pontos.dto.Planos;
import br.com.gomesdev87.shippafestApi.user.User;
import br.com.gomesdev87.shippafestApi.user.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PontoService {
    private final PontosRepository pontosRepository;
    private final UserRepository userRepository;

    public PontoService(PontosRepository pontosRepository, UserRepository userRepository) {
        this.pontosRepository = pontosRepository;
        this.userRepository = userRepository;
    }

    public Optional<Pontos> getPontos(String idUser, PlanoConfig config){
        User user = userRepository.findById(idUser)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        Optional<Pontos> pontos = pontosRepository.findByUser(user);
        Pontos pontosAtual = new Pontos();
        LocalDate hoje = LocalDate.now();

        if(pontos.isPresent()){
            pontosAtual = pontos.get();
            if(pontosAtual.getExpiryDate().isBefore(hoje) ){
                Planos planos = new Planos("free");
                PlanoConfig config2 = planos.planoOp();
                pontosAtual.setPlano(config2.plano());
                pontosAtual.setLikes(config2.likes());
                pontosAtual.setSuperLikes(config2.superLikes());
                pontosAtual.setVoltars(config2.voltars());
                pontosAtual.setMyLikes(config2.myLikes());
                pontosAtual.setExpiryDate(config2.expiryDate());
                pontosAtual.setDateUpdate(config2.dateUpdate());
            }
            else if(pontosAtual.getDateUpdate().isBefore(hoje)){
                Planos planos1 = new Planos(pontosAtual.getPlano());
                PlanoConfig config3 = planos1.planoOp();
                pontosAtual.setLikes(config3.likes());
                pontosAtual.setVoltars(config3.voltars());
                pontosAtual.setSuperLikes(config3.superLikes());
                pontosAtual.setMyLikes(config3.myLikes());
                pontosAtual.setExpiryDate(config3.expiryDate());
                pontosAtual.setDateUpdate(config3.dateUpdate());
            }

        }
        else {
            pontosAtual.setUser(user);
            pontosAtual.setPlano(config.plano());
            pontosAtual.setLikes(config.likes());
            pontosAtual.setVoltars(config.voltars());
            pontosAtual.setMyLikes(config.myLikes());
            pontosAtual.setExpiryDate(config.expiryDate());
            pontosAtual.setDateUpdate(config.dateUpdate());
        }

        pontosRepository.save(pontosAtual);

        return Optional.of((Pontos) pontosAtual);
    }
}
