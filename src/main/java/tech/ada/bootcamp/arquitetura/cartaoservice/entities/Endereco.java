package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.EnderecoRequest;

@Data
@Entity
@Table(name = "endereco")
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;
    private String numero;
    @OneToOne
    @JoinColumn(name = "principal_identificador")
    private Principal principal;

    public Endereco(EnderecoRequest dto, Principal principal) {
        this.cep = dto.cep();
        this.rua = dto.rua();
        this.bairro = dto.bairro();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
        this.complemento = dto.complemento();
        this.numero = dto.numero();
        this.principal = principal;
    }
}
