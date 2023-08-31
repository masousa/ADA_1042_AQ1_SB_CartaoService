package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

import java.util.List;

public record CadastroPrincipalRequest (

        //@CPF
        @NotBlank
        @Pattern(regexp = "^\\d{11}$")
        String identificador,
        @NotBlank
        String nome,
        @NotNull
        @Valid
        EnderecoRequest endereco,
        @NotNull
        TipoCartao tipoCartao){
}
