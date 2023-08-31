package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CriarNovoCartaoService;

@RestController
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {

    @PostMapping(path = "", produces = "application/json")
    public CadastroUsuarioResponse cadastrarUsuario(@RequestBody CadastroUsuarioRequest cadastroUsuarioRequest) {
        return new CadastroUsuarioResponse();
    }

    @PostMapping(path = "/dependente/{idUsuario}", produces = "application/json")
    public CadastroUsuarioResponse adicionarDependente(@RequestBody CadastroUsuarioRequest cadastroUsuarioRequest, @PathVariable("idUsuario") String idUsuario) {
        return new CadastroUsuarioResponse();
    }

}
