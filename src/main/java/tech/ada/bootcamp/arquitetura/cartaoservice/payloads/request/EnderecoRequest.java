package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoRequest (
        @NotBlank
        @Pattern(regexp = "^\\d{8}$")
        String cep,
        @NotBlank
        String rua,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank
        String estado,
        String complemento,
        @NotBlank
        String numero) {
}
