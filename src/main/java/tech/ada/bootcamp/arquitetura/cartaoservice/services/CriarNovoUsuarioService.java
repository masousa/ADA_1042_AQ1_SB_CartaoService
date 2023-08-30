package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Endereco;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.EnderecoRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.UsuarioRepository;

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
}
