package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraResponse {
    private String numeroCartao;
    private String loja;
    private double valor;

    private String statusCompra;
}
