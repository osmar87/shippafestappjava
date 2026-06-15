package br.com.gomesdev87.shippafestApi.galeria;

import br.com.gomesdev87.shippafestApi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GaleriaRepository extends JpaRepository<Galeria, String> {
    Optional<Galeria> findByUser(User user);
    Optional<Galeria> findByUser_Id(String id);

}
