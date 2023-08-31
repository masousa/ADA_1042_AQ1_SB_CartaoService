package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroDependenteRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroPrincipalRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.presenters.CriarCartaoPresenter;

@RestController
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {
    private CriarCartaoPresenter cartaoPresenter;
    public UsuarioController (CriarCartaoPresenter cartaoPresenter) {
        this.cartaoPresenter = cartaoPresenter;
    }
    @PostMapping(path = "", produces = "application/json" )
    public CadastroUsuarioResponse cadastrarUsuario(@RequestBody CadastroPrincipalRequest dto){
        return this.cartaoPresenter.execute(dto);
    }

    @PostMapping(path = "/dependente", produces = "application/json" )
    public CadastroUsuarioResponse adicionarDependente(@RequestBody CadastroDependenteRequest dto){
        return this.cartaoPresenter.execute(dto);
    }

}
