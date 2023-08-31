package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Compra;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Fatura;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.NotFoundException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.FaturaResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CompraRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.FaturaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class VerFaturaService {
    private final FaturaRepository faturaRepository;
    private final CompraRepository compraRepository;

    public FaturaResponse verFaturaCartao(String numeroCartao, Integer mes, Integer ano) {
        LocalDate dataProcessamento = LocalDate.of(ano, mes, 1);
        List<Fatura> listaFaturas = faturaRepository.findAll();
        List<Compra> listaCompras = compraRepository.findAll();

        if (!listaFaturas.isEmpty()) {
            Fatura faturaPorMes =
                listaFaturas.stream()
                    .filter(f -> Objects.equals(f.getCartao().getNumeroCartao(), numeroCartao)
                        && f.getDataProcessamento() == dataProcessamento)
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("Fatura"));

            List<CompraResponse> listaCompraResponse = listaDeComprasPorMes(listaCompras,
                numeroCartao, mes, ano, dataProcessamento);

            return new FaturaResponse(faturaPorMes.getValor().doubleValue(),
                faturaPorMes.getDataProcessamento(),
                faturaPorMes.getDataVencimento(), listaCompraResponse);
        }

        return new FaturaResponse();
    }

    private List<CompraResponse> listaDeComprasPorMes(List<Compra> lista, String numeroCartao,
                                                      Integer mes, Integer ano,
                                                      LocalDate dataProcessamento) {
        List<Compra> listaComprasPorCartaoPorMes = lista.stream()
            .filter(c -> Objects.equals(c.getCartao().getNumeroCartao(), numeroCartao)
                && c.getDataCompra().isBefore(LocalDateTime.of(dataProcessamento,
                LocalTime.now()))
                && c.getDataCompra().isAfter(LocalDateTime.of(dataProcessamento.minusMonths(1),
                LocalTime.now())))
            .toList();

        return listaComprasPorCartaoPorMes.stream()
            .map(compra -> new CompraResponse(compra.getCartao().getNumeroCartao(),
                compra.getLoja(), compra.getValor().doubleValue(),
                compra.getStatusCompra().toString()))
            .toList();
    }
}
