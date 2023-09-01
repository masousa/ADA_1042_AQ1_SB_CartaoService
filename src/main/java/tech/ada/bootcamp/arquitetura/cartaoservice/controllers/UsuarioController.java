package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroDependenteRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroPrincipalRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.usecase.CriarCartaoUseCase;

@RestController
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {
    private CriarCartaoUseCase cartaoUseCase;
    public UsuarioController (CriarCartaoUseCase cartaoPresenter) {
        this.cartaoUseCase = cartaoPresenter;
    }
    @PostMapping(path = "", produces = "application/json" )
    public CadastroUsuarioResponse cadastrarUsuario(@RequestBody @Valid CadastroPrincipalRequest dto){
        return this.cartaoUseCase.execute(dto);
    }

    @PostMapping(path = "/dependente", produces = "application/json" )
    public CadastroUsuarioResponse adicionarDependente(@RequestBody @Valid CadastroDependenteRequest dto){
        return this.cartaoUseCase.execute(dto);
    }

}
