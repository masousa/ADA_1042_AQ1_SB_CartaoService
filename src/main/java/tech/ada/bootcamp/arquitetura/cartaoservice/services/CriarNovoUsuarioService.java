package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Endereco;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.EnderecoRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CriarNovoUsuarioService {
    private UsuarioRepository usuarioRepository;
    private EnderecoRepository enderecoRepository;
    public CriarNovoUsuarioService (UsuarioRepository usuarioRepository, EnderecoRepository enderecoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public Usuario execute(CadastroUsuarioRequest cadastroUsuarioRequest) {
        var usuario = new Usuario(cadastroUsuarioRequest);
        var endereco = new Endereco(cadastroUsuarioRequest.endereco(), usuario);
        usuarioRepository.save(usuario);
        enderecoRepository.save(endereco);
        return usuario;
    }

    public List<Usuario> execute(List<String> dependentes, CadastroUsuarioRequest dto) {
        List<Usuario> lista = new ArrayList<Usuario>();
        for (var dependente : dependentes) {
            var identificadorDependente = dto.identificador() + "-" + UUID.randomUUID();
            var dtoDependente = new CadastroUsuarioRequest(identificadorDependente, dependente, dto.endereco(), dto.tipoCartao(), null);
            var usuario = new Usuario(dtoDependente);
            var endereco = new Endereco(dtoDependente.endereco(), usuario);
            usuarioRepository.save(usuario);
            enderecoRepository.save(endereco);
            lista.add(usuario);
        }
        return lista;
    }
}
