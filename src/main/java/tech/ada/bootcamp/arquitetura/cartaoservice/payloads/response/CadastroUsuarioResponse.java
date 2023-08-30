package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroUsuarioResponse {
    private String numeroCartao;
    private String nomeTitularCartao;

    private TipoCartao tipoCartao;

    private String nomeTitularConta;
}
