package br.com.gomesdev87.shippafestApi.galeria;

import br.com.gomesdev87.shippafestApi.galeria.dto.GaleriaRequet;
import br.com.gomesdev87.shippafestApi.galeria.dto.GaleriaResponse;
import br.com.gomesdev87.shippafestApi.utils.ImageUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/galeria")
public class GaleriaController {
    private final ImageUtils imageUtils;
    private final GaleriaRepository galeriaRepository;

    public GaleriaController(ImageUtils imageUtils, GaleriaRepository galeriaRepository) {
        this.imageUtils = imageUtils;
        this.galeriaRepository = galeriaRepository;
    }

    @PostMapping( consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<GaleriaResponse> createGaleria(
            @Valid @ModelAttribute GaleriaRequet request
    ) {
        Galeria galeria = new Galeria();
        galeria.setIdUser(request.idUser());
        // Exemplo:
        if (request.foto() != null && !request.foto().isEmpty()) {
            try {
                String nomeImagem = imageUtils.salvarImagem(request.foto(), "users");
                galeria.setFoto(nomeImagem);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao salvar a imagem.", e);
            }
        }
        galeriaRepository.save(galeria);

        return ResponseEntity.status(HttpStatus.CREATED).body(new GaleriaResponse(galeria));
    }
}
