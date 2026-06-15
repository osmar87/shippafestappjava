package br.com.gomesdev87.shippafestApi.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;

@Component
public class ImageUtils {
    @Value("${app.upload.dir}")
    private String uploadDir;

    private static final Set<String> TIPOS_PERMITIDOS = Set.of(
            "image/jpeg",
            "image/png",
            "image/webp"
    );

    private static final long TAMANHO_MAXIMO = 10 * 1024 * 1024; // 10 MB



    public String salvarImagem(MultipartFile arquivo, String pasta) throws IOException {
        validarImagem(arquivo);

        try {
            Path diretorio = Paths.get(uploadDir, pasta).toAbsolutePath();

            Files.createDirectories(diretorio);

            String extensao = obterExtensao(arquivo.getOriginalFilename());
            String nomeArquivo = UUID.randomUUID() + "." + extensao;

            Path destino = diretorio.resolve(nomeArquivo);

            Files.copy(
                    arquivo.getInputStream(),
                    destino,
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );

            return pasta + "/" + nomeArquivo;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar a imagem.", e);
        }
    }

    public void validarImagem(MultipartFile arquivo) {

        if (arquivo == null || arquivo.isEmpty()) {
            throw new IllegalArgumentException("A imagem é obrigatória.");
        }

        if (!TIPOS_PERMITIDOS.contains(arquivo.getContentType())) {
            throw new IllegalArgumentException("Formato de imagem não suportado.");
        }

        if (arquivo.getSize() > TAMANHO_MAXIMO) {
            throw new IllegalArgumentException("A imagem não pode ultrapassar 10MB.");
        }
    }

    private String obterExtensao(String nomeArquivo) {
        if (nomeArquivo == null || !nomeArquivo.contains(".")) {
            return "jpg";
        }

        return nomeArquivo.substring(nomeArquivo.lastIndexOf('.') + 1);
    }
}