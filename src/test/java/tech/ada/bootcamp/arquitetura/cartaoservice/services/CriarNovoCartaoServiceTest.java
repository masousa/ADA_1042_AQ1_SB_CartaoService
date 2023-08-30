package tech.ada.bootcamp.arquitetura.cartaoservice.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class CriarNovoCartaoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private CriarNovoCartaoService criarNovoCartaoService;

    @Test
    void shouldSaveSuccessfullyANewCard(){
        CadastroUsuarioRequest cadastroUsuarioRequest = Mockito.mock(CadastroUsuarioRequest.class);

        TipoCartao randomTipoCartao = TipoCartao.values()[(int)Math.random() * TipoCartao.values().length];
        String randomIdentificador = Faker.instance().regexify("[0-9]{11}");
        String randomNome = Faker.instance().name().fullName();

        Mockito.when(cadastroUsuarioRequest.getTipoCartao()).thenReturn(randomTipoCartao);
        Mockito.when(cadastroUsuarioRequest.getIdentificador()).thenReturn(randomIdentificador);
        Mockito.when(cadastroUsuarioRequest.getNome()).thenReturn(randomNome);

        criarNovoCartaoService.execute(cadastroUsuarioRequest);

        ArgumentCaptor<Cartao> cartaoArgumentCaptor = ArgumentCaptor.forClass(Cartao.class);
        Mockito.verify(cartaoRepository,Mockito.times(1))
                .save(cartaoArgumentCaptor.capture());
        Cartao cartaoSalvo = cartaoArgumentCaptor.getValue();
        
        Assertions.assertEquals(LocalDate.now().plusYears(5), cartaoSalvo.getVencimentoCartao());
        Assertions.assertEquals(3,cartaoSalvo.getCodigoSeguranca().length());
        Assertions.assertEquals(12, cartaoSalvo.getNumeroCartao().length());
        Assertions.assertEquals(randomTipoCartao, cartaoSalvo.getTipoCartao());
        Assertions.assertEquals(randomIdentificador, cartaoSalvo.getUsuario().getIdentificador());
        Assertions.assertEquals(randomNome, cartaoSalvo.getNomeTitular());
        Assertions.assertEquals(randomNome, cartaoSalvo.getUsuario().getNome());

    }
}
