package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

import java.util.List;

public record CadastroDependenteRequest (String identificador, String identificadorTitular, String nome, TipoCartao tipoCartao){
}
