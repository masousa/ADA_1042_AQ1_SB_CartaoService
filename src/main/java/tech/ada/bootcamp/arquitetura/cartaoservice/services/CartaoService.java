package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Dependente;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Principal;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroDependenteRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroPrincipalRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class CartaoService {
    private UsuarioService usuarioService;
    private CartaoRepository cartaoRepository;
    private static Random random;

    public CartaoService(UsuarioService usuarioService, CartaoRepository cartaoRepository) {
        this.usuarioService = usuarioService;
        this.cartaoRepository = cartaoRepository;
    }


    public List<Object> execute(CadastroPrincipalRequest dto){
        Principal titular = usuarioService.execute(dto);
        List<Object> listaCartoesCadastrados = new ArrayList<Object>();

        var cartao = criarCartao(titular, dto.tipoCartao());
        var cartaoCadastrado = cartaoRepository.save(cartao);
        listaCartoesCadastrados.add(cartaoCadastrado.dto(titular.getNome()));
        var listaCartoesDependentes = execute(dto);
        listaCartoesCadastrados.add(listaCartoesDependentes);
        return listaCartoesCadastrados;
    }

    public List<CadastroUsuarioResponse> execute(CadastroDependenteRequest dto){
        Dependente dependente = usuarioService.execute(dto);
        List<CadastroUsuarioResponse> listaCartoesCadastrados = new ArrayList<CadastroUsuarioResponse>();

        var cartao = criarCartao(dependente, dto.tipoCartao());
        var cartaoCadastrado = cartaoRepository.save(cartao);
        listaCartoesCadastrados.add(cartaoCadastrado.dto(dependente.getNome()));

        return listaCartoesCadastrados;
    }



    private Cartao criarCartao(Usuario usuario, TipoCartao tipoCartao) {
        LocalDate dataAtual = LocalDate.now();
        Cartao cartao = new Cartao();
        cartao.setTipoCartao(tipoCartao);
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
