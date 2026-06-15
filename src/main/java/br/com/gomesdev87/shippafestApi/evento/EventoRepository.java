package br.com.gomesdev87.shippafestApi.evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, String> {

    @Query(value = "SELECT * FROM eventos e " +
            "ORDER BY (6371 * acos( " +
            "    cos(radians(:userLat)) * cos(radians(CAST(e.latitude AS DOUBLE PRECISION))) * " +
            "    cos(radians(CAST(e.longitude AS DOUBLE PRECISION)) - radians(:userLon)) + " +
            "    sin(radians(:userLat)) * sin(radians(CAST(e.latitude AS DOUBLE PRECISION))) " +
            ")) ASC",
            nativeQuery = true)
    List<Evento> buscarEventosMaisProximos(@Param("userLat") double userLat,
                                           @Param("userLon") double userLon);
}