package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "cartao")
public class Cartao {
    @Id
    private String numeroCartao;
    private String nomeTitular;
    private LocalDate vencimentoCartao;

    private String codigoSeguranca;

    private TipoCartao tipoCartao;

    private String idContaBanco;

    @ManyToOne
    @JoinColumn(name = "principal_identificador")
    private Principal principal;

    @ManyToOne
    @JoinColumn(name = "dependente_identificador")
    private Dependente dependente;

    public CadastroUsuarioResponse dto(String nome) {
        return new CadastroUsuarioResponse(
                this.numeroCartao,
                this.nomeTitular,
                this.tipoCartao,
                nome
        );
    }
}