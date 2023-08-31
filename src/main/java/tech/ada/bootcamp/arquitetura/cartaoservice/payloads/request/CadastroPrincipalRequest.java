package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

import java.util.List;

public record CadastroPrincipalRequest (String identificador, String nome, EnderecoRequest endereco, TipoCartao tipoCartao, List<String> dependentes){
}
