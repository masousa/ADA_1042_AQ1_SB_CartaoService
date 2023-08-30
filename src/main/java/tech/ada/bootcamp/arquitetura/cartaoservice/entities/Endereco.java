package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "endereco")
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
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuarioIdentificador")
    private Usuario usuario;
}
