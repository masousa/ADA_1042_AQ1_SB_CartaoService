package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

@Getter
@Setter
public class CadastroUsuarioRequest {
    private String identificador;

    private String nome;
    private EnderecoRequest enderecoRequest;

    private TipoCartao tipoCartao;

    private List<String> dependentes;

}
