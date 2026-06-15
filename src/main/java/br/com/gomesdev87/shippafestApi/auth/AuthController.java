package br.com.gomesdev87.shippafestApi.auth;

import br.com.gomesdev87.shippafestApi.config.TokenCoonfig;
import br.com.gomesdev87.shippafestApi.auth.dto.request.LoginRequest;
import br.com.gomesdev87.shippafestApi.auth.dto.request.RegisterRequest;
import br.com.gomesdev87.shippafestApi.auth.dto.response.RegisterUserResponse;
import br.com.gomesdev87.shippafestApi.user.SignosServices;
import br.com.gomesdev87.shippafestApi.user.User;
import br.com.gomesdev87.shippafestApi.user.UserRepository;
import br.com.gomesdev87.shippafestApi.utils.ImageUtils;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private SignosServices signosServices;

    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenCoonfig tokenCoonfig;
    private final ImageUtils imageUtils;

    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, TokenCoonfig tokenCoonfig, ImageUtils imageUtils
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenCoonfig = tokenCoonfig;
        this.imageUtils = imageUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<RegisterUserResponse> login(@Valid @RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        Authentication authentication = authenticationManager.authenticate(userAndPass);
        User user = (User)authentication.getPrincipal();

        String token = tokenCoonfig.genereToken(user);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RegisterUserResponse(user, token));
    }

    @PostMapping(
            value = "/register",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<RegisterUserResponse> register(
            @Valid @ModelAttribute RegisterRequest request
    ) {

        User newuser = new User();
        newuser.setNome(request.nome());
        newuser.setNasc(request.nasc());
        newuser.setSenha(passwordEncoder.encode(request.senha()));
        newuser.setEmail(request.email());
        newuser.setGenero(request.genero());
        newuser.setSexo(request.sexo());
        newuser.setSigno(signosServices.calcularSigno(newuser.getNasc()));

        // Exemplo:
         if (request.perfil() != null && !request.perfil().isEmpty()) {
             try {
                 String nomeImagem = imageUtils.salvarImagem(request.perfil(), "users");
                 newuser.setPerfil(nomeImagem);
             } catch (IOException e) {
                 throw new RuntimeException("Erro ao salvar a imagem.", e);
             }
         }

        userRepository.save(newuser);

        String token = tokenCoonfig.genereToken(newuser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new RegisterUserResponse(newuser, token));
    }
}
