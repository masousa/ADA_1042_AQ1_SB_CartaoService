package tech.ada.bootcamp.arquitetura.cartaoservice.usecase;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Dependente;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Principal;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroDependenteRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroPrincipalRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CartaoService;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.UsuarioService;

@Service
public class CriarCartaoUseCase {
    private UsuarioService usuarioService;
    private CartaoService cartaoService;
    public CriarCartaoUseCase(UsuarioService usuarioService, CartaoService cartaoService) {
        this.usuarioService = usuarioService;
        this.cartaoService = cartaoService;
    }

    public CadastroUsuarioResponse execute(CadastroPrincipalRequest dto){
        Principal titular = usuarioService.criarPrincipal(dto);
        return cartaoService.cartaoTitular(titular, dto.tipoCartao());
    }

    public CadastroUsuarioResponse execute(CadastroDependenteRequest dto){
        Principal titular = usuarioService.getPrincipal(dto.identificadorTitular());
        Dependente dependente = usuarioService.criarDependente(dto);
        return cartaoService.cartaoDependente(dependente, titular, dto.tipoCartao());
    }

}
