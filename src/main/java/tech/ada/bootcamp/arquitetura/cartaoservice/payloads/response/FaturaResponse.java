package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FaturaResponse {
    private double valor;
    private LocalDate dataFaturaGerada;

    private LocalDate referenciaFatura;

    private List<CompraResponse> resumoCompra;


}
