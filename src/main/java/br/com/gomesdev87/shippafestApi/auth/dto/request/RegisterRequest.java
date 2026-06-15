package br.com.gomesdev87.shippafestApi.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record RegisterRequest(
        @NotBlank(message = "O nome é Obrigatorio") String nome,
        @NotBlank(message = "O e-mail é Obrigatorio") String email,
        @NotBlank(message = "A Senha é Obrigatorio")
        @Size(
                min = 6,
                message = "Senha muito curta. Ela deve ter no mínimo 6 caracteres."
        )
        String senha,
        @NotBlank(message = "A Sexo é Obrigatorio") String sexo,
        @NotNull(message = "Data de nascimento é obrigatória")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate nasc,
        @NotBlank(message = "A Genero é Obrigatorio") String genero,
        MultipartFile perfil
) {
}
