package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.FaturaResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.VerFaturaService;

@RestController
@RequestMapping("/fatura")
@RequiredArgsConstructor
@Slf4j
public class VerFaturaController {
    private final VerFaturaService faturaService;

    @GetMapping(path = "/{numeroCartao}/{mes}/{ano}", produces = "application/json" )
    public FaturaResponse verFaturaCartao(@PathVariable("numeroCartao") String numeroCartao
            , @PathVariable("mes") Integer mes, @PathVariable("ano") Integer ano){
        return faturaService.verFaturaCartao(numeroCartao, mes, ano);
    }

}
