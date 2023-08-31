package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    public Dependente(CadastroDependenteRequest dto) {
        this.identificador = dto.identificador();
        this.nome = dto.nome();
    }
}
