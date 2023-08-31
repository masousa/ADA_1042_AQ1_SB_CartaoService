package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CriarNovoCartaoService;

@RestController
@RequestMapping("/cartao")
@Slf4j
public class CartaoController {
    private final CriarNovoCartaoService cartaoService;

    public CartaoController(CriarNovoCartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @PostMapping(path = "", produces = "application/json")
    public CadastroUsuarioResponse adicionarCartao(@RequestBody CadastroUsuarioRequest cadastroUsuarioRequest) {
        return this.cartaoService.execute(cadastroUsuarioRequest);
    }
}
