package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CriarNovoCartaoService;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CriarNovoUsuarioService;

@RestController
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {
    private CriarNovoCartaoService criarNovoCartaoService;
    public UsuarioController (CriarNovoCartaoService cartaoService) {
        this.criarNovoCartaoService = cartaoService;
    }
    @PostMapping(path = "", produces = "application/json" )
    public CadastroUsuarioResponse cadastrarUsuario(@RequestBody CadastroUsuarioRequest cadastroUsuarioRequest){
        return this.criarNovoCartaoService.execute(cadastroUsuarioRequest);
    }

    @PostMapping(path = "/dependente/{idUsuario}", produces = "application/json" )
    public void adicionarDependente(@RequestBody CadastroUsuarioRequest cadastroUsuarioRequest, @PathVariable("idUsuario") String idUsuario){

    }

}
