package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime dataCompra;
    private String loja;

    public BigDecimal valor;


    @ManyToOne
    @JoinColumn(name = "numeroCartao")
    public Cartao cartao;

    public StatusCompra statusCompra;

}