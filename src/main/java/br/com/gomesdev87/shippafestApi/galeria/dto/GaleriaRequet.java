package br.com.gomesdev87.shippafestApi.galeria.dto;

import org.springframework.web.multipart.MultipartFile;

public record GaleriaRequet(MultipartFile foto, String idUser) {
}
