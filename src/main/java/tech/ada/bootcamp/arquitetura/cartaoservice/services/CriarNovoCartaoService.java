package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.NotFoundException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.UsuarioRepository;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CriarNovoCartaoService {
    private final CartaoRepository cartaoRepository;
    private final UsuarioRepository usuarioRepository;
    private static Random random;

    public CadastroUsuarioResponse execute(CadastroUsuarioRequest cadastroUsuarioRequest) {
        LocalDate dataAtual = LocalDate.now();
        Cartao cartao = new Cartao();

        cartao.setTipoCartao(cadastroUsuarioRequest.getTipoCartao());
        Usuario usuario = usuarioRepository.findById(cadastroUsuarioRequest.getIdentificador())
            .orElseThrow(() -> new NotFoundException("Usuario"));
        cartao.setUsuario(usuario);
        cartao.setIdContaBanco(UUID.randomUUID().toString());
        cartao.setNomeTitular(cadastroUsuarioRequest.getNome());
        cartao.setVencimentoCartao(dataAtual.plusYears(5));
        cartao.setCodigoSeguranca(gerarNumeroAleatorio(3));
        cartao.setNumeroCartao(gerarNumeroAleatorio(12));
        cartaoRepository.save(cartao);
        return new CadastroUsuarioResponse(cartao.getNumeroCartao(), cartao.getNomeTitular(),
            cartao.getTipoCartao(), usuario.getNome());
    }

    private String gerarNumeroAleatorio(int length) {

        final int tamanho = length <= 0 ? 1 : length;
        IntStream stream = getRandom().ints(tamanho, 0, 9);
        StringBuilder builder = new StringBuilder();
        stream.forEachOrdered(builder::append);
        return builder.toString();
    }

    private static Random getRandom() {
        if (Objects.isNull(random)) {
            random = new Random();
        }
        return random;
    }
}
