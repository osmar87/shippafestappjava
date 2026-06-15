package br.com.gomesdev87.shippafestApi.pontos;


import br.com.gomesdev87.shippafestApi.pontos.dto.PlanoConfig;
import br.com.gomesdev87.shippafestApi.pontos.dto.PlanoResponse;
import br.com.gomesdev87.shippafestApi.pontos.dto.Planos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plano")
public class PontosController {

    private final PontoService pontoService;

    public PontosController(PontoService pontoService) {
        this.pontoService = pontoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoResponse> test(
            @PathVariable String id
    ){

        Planos planos = new Planos("free");
        PlanoConfig config = planos.planoOp();
        return ResponseEntity.ok(new PlanoResponse(pontoService.getPontos(id, config), id));
    }
}
