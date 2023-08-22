package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "fatura")
public class Fatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate dataVencimento;

    private LocalDate dataProcessamento;

    private BigDecimal valor;

    private BigDecimal valorPago;

    @ManyToOne
    @JoinColumn(name = "numeroCartao")
    private Cartao cartao;

}