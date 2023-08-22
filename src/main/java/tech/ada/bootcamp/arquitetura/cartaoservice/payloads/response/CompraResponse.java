package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import lombok.Data;

@Data
public class CompraResponse {
    private String numeroCartao;
    private String loja;
    private double valor;

    private String statusCompra;
}
