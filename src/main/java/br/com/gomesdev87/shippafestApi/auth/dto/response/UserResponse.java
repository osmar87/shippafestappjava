package br.com.gomesdev87.shippafestApi.auth.dto.response;

import br.com.gomesdev87.shippafestApi.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponse(
        String id,
        String nome,
        LocalDate nasc,
        String email,
        String sexo,
        String genero,
        String perfil,
        String cidade,
        String estado,
        String bio,
        String telefone,
        Boolean whatsapp,
        Boolean status,
        Integer type,
        LocalDateTime createat,
        String signo
) {
    public UserResponse(User user) {
        this(
                user.getId(),
                user.getNome(),
                user.getNasc(),
                user.getEmail(),
                user.getSexo(),
                user.getGenero(),
                user.getPerfil(),
                user.getCidade(),
                user.getEstado(),
                user.getBio(),
                user.getTelefone(),
                user.getWhatsapp(),
                user.getStatus(),
                user.getType(),
                user.getCreateat(),
                user.getSigno()
        );
    }
}