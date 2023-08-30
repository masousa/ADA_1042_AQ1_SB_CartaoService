package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.FaturaResponse;

@RestController
@RequestMapping("/fatura")
@Slf4j
public class VerFaturaController {

    @GetMapping(path = "/{numeroCartao}/{mes}/{ano}", produces = "application/json" )
    public FaturaResponse adicionarDependente(@PathVariable("numeroCartao") String numeroCartao
            , @PathVariable("mes") Integer mes, @PathVariable("ano") Integer ano){
        return new FaturaResponse();
    }

}
