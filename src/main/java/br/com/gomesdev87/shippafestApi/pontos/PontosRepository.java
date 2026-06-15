package br.com.gomesdev87.shippafestApi.pontos;

import br.com.gomesdev87.shippafestApi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PontosRepository extends JpaRepository<Pontos, String> {
    Optional<Pontos> findByUser(User user);
    Optional<Pontos> findByUser_Id(String id);
}
