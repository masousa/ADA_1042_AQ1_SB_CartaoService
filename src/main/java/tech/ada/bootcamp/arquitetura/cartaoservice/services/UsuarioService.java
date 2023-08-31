package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Dependente;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Principal;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroDependenteRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroPrincipalRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.DependenteRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.EnderecoRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.PrincipalRepository;

import java.util.List;

@Service
public class CriarNovoUsuarioService {
    public CriarNovoUsuarioService(PrincipalRepository principaçRepository, DependenteRepository dependenteRepository, EnderecoRepository enderecoRepository) {
        this.principalRepository = principaçRepository;
        this.dependenteRepository = dependenteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    private PrincipalRepository principalRepository;
    private DependenteRepository dependenteRepository;
    private EnderecoRepository enderecoRepository;


    public Dependente execute(CadastroDependenteRequest dto) {
        var user = new Dependente(dto);
        dependenteRepository.save(user);
//        var endereco = new Endereco(dto.endereco(), user);
//        enderecoRepository.save(endereco);
        return user;
    }

    public Principal execute(CadastroPrincipalRequest  dto) {
        var user = new Principal(dto);
        principalRepository.save(user);
//        var endereco = new Endereco(dto.endereco(), user);
//        enderecoRepository.save(endereco);
        return user;
    }

    public List<Principal> getAllPrincipal() {
        return principalRepository.findAll();
    }

    public List<Dependente> getAllDependente() {
        return dependenteRepository.findAll();
    }
}
