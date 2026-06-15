package br.com.gomesdev87.shippafestApi.galeria;

import br.com.gomesdev87.shippafestApi.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "galerias")
public class Galeria {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String foto;

    @Column(name = "id_user", nullable = false)
    private String idUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_user",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    @JsonBackReference
    private User user;

    // getters e setters
}