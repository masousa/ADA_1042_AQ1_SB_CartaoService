package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroDependenteRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;

import java.util.Set;

@Data
@Entity
@Table(name = "dependente")
@NoArgsConstructor
public class Dependente extends Usuario{
    @Id
    private String identificador;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "principal_identificador")
    private Principal principal;

    public Dependente(CadastroDependenteRequest dto, Principal principal) {
        this.identificador = dto.identificador();
        this.nome = dto.nome();
        this.principal = principal;
    }
}
