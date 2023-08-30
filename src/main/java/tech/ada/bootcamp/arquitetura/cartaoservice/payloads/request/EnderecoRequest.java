package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequest {
  private Long identificador;
  private String cep;
  private String rua;
  private String bairro;
  private String cidade;
  private String estado;
  private String complemento;
  private String numero;
}
