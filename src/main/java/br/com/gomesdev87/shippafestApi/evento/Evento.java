package br.com.gomesdev87.shippafestApi.evento;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "eventos", indexes = {
        @Index(name = "idx_lat_long", columnList = "latitude, longitude")
})
@Getter
@Setter
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    private String fotoCapa;

    private  String cidade;

    private String estado;

    private LocalDate dataInicio;

    private String hora;

    private String local;

    private boolean ativa;

    private  int totalUser;

    private int totalView;

    private String descricao;

    private String descricao01;

    private BigDecimal latitude;

    private BigDecimal longitude;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createat;

    @JsonIgnore
    @PrePersist
    public void prePersist() {
        if (createat == null) {
            createat = LocalDateTime.now();
            ativa = true;
        }
    }

}
