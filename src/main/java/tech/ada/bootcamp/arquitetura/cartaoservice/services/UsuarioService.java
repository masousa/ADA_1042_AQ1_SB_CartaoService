package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Dependente;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Endereco;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Principal;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroDependenteRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroPrincipalRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.DependenteRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.EnderecoRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.PrincipalRepository;

import java.util.List;

@Service
public class UsuarioService {
    private PrincipalRepository principalRepository;
    private DependenteRepository dependenteRepository;
    private EnderecoRepository enderecoRepository;

    public UsuarioService(PrincipalRepository principalRepository, DependenteRepository dependenteRepository, EnderecoRepository enderecoRepository) {
        this.principalRepository = principalRepository;
        this.dependenteRepository = dependenteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public Dependente execute(CadastroDependenteRequest dto) {
        var principal = principalRepository.findById(dto.identificadorTitular());
        if (principal.isEmpty()) {
            //erro
        }
        var dependente = new Dependente(dto, principal.get());
        dependenteRepository.save(dependente);
        return dependente;
    }

    public Principal execute(CadastroPrincipalRequest  dto) {
        var principal = new Principal(dto);
        principalRepository.save(principal);
        var endereco = new Endereco(dto.endereco(), principal);
        enderecoRepository.save(endereco);
        return principal;
    }

    public Principal getPrincipal(String idTitular) {
        var principalOp = principalRepository.findById(idTitular);
        if(principalOp.isEmpty()) {
            //Erro
        }
        return principalOp.get();
    }
    public List<Principal> getAllPrincipal() {
        return principalRepository.findAll();
    }

    public List<Dependente> getAllDependente() {
        return dependenteRepository.findAll();
    }
}
