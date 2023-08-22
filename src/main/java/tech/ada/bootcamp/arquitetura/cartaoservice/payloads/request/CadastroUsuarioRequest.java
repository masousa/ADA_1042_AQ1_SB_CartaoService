package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import lombok.Getter;
import lombok.Setter;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CadastroUsuarioRequest {
    private String identificador;

    private String nome;
    private EnderecoRequest enderecoRequest;

    private TipoCartao tipoCartao;

    private List<String> dependentes;

}
