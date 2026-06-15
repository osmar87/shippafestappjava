package br.com.gomesdev87.shippafestApi.pontos;

import br.com.gomesdev87.shippafestApi.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table
public class Pontos {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String plano;

    private int likes;

    private int superLikes;

    private int voltars;

    private boolean myLikes;

    private LocalDate expiryDate;

    private LocalDate dateUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @JsonBackReference
    private User user;
}