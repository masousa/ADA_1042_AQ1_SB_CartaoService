package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

import java.util.List;

public record CadastroDependenteRequest (

        //@CPF
        @NotBlank
        @Pattern(regexp = "^\\d{11}$")
        String identificador,
        @NotBlank
        @Pattern(regexp = "^\\d{11}$")
        //@CPF
        String identificadorTitular,
        @NotBlank
        String nome,
        @NotNull
        TipoCartao tipoCartao){
}
