package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import lombok.Data;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

public record CadastroUsuarioResponse (String numeroCartao, String nomeTitularCartao, TipoCartao tipoCartao, String nomeTitularConta){}
