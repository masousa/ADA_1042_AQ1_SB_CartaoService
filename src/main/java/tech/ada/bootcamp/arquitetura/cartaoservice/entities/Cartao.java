package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

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

    private Boolean dependente = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "usuarioIdentificador")
    private Usuario usuario;

}