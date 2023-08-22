package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import lombok.Data;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

@Data
public class CadastroUsuarioResponse {
    private String numeroCartao;
    private String nomeTitularCartao;

    private TipoCartao tipoCartao;

    private String nomeTitularConta;
}
