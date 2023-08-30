package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class CriarNovoCartaoService {
    private CriarNovoUsuarioService criarNovoUsuarioService;
    private CartaoRepository cartaoRepository;
    private static Random random;

    public CriarNovoCartaoService(CriarNovoUsuarioService criarNovoUsuarioService, CartaoRepository cartaoRepository) {
        this.criarNovoUsuarioService = criarNovoUsuarioService;
        this.cartaoRepository = cartaoRepository;
    }
    public List<CadastroUsuarioResponse> execute(CadastroUsuarioRequest dto){
        Usuario usuario = criarNovoUsuarioService.execute(dto);
        List<CadastroUsuarioResponse> listaCartoesCadastrados = new ArrayList<CadastroUsuarioResponse>();

        var cartao = criarCartao(usuario, dto);
        var cartaoCadastrado = cartaoRepository.save(cartao);
        listaCartoesCadastrados.add(cartaoCadastrado.dto(null));

        if (dto.dependentes() != null && !dto.dependentes().isEmpty()) {
            List<Usuario> usuariosDep = criarNovoUsuarioService.execute(dto.dependentes(), dto);
            for (var dependente : usuariosDep) {
                var cartaoDep = criarCartao(dependente, dto);
                cartaoDep.setDependente(true);
                var cartaoCadastradoDep = cartaoRepository.save(cartaoDep);
                listaCartoesCadastrados.add(cartaoCadastradoDep.dto(dependente.getNome()));
            }
        }

        return listaCartoesCadastrados;
    }

    private Cartao criarCartao(Usuario usuario, CadastroUsuarioRequest dto) {
        LocalDate dataAtual = LocalDate.now();
        Cartao cartao = new Cartao();
        cartao.setTipoCartao(dto.tipoCartao());
        cartao.setUsuario(usuario);
        cartao.setIdContaBanco(UUID.randomUUID().toString());
        cartao.setNomeTitular(usuario.getNome());
        cartao.setVencimentoCartao(dataAtual.plusYears(5));
        cartao.setCodigoSeguranca(gerarNumeroAleatorio(3));
        cartao.setNumeroCartao(gerarNumeroAleatorio(12));
        return cartao;
    }

    private String gerarNumeroAleatorio(int length) {

        final int tamanho = length<=0?1:length;
        IntStream stream =  getRandom().ints(tamanho,0,9);
        StringBuilder builder = new StringBuilder();
        stream.forEachOrdered(builder::append);
        return builder.toString();
    }

    private static Random getRandom(){
        if(Objects.isNull(random)){
            random = new Random();
        }
        return random;
    }
}
