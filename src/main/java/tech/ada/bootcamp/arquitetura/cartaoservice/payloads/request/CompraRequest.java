package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import lombok.Data;

@Data
public class CompraRequest {
    private String numeroCartao;
    private String loja;
    private double valor;
}
