package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaturaResponse {
    private double valor;
    private LocalDate dataFaturaGerada;

    private LocalDate referenciaFatura;

    private List<CompraResponse> resumoCompra;


}
