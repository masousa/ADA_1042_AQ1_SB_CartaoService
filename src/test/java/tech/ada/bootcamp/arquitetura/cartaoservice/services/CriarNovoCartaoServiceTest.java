package tech.ada.bootcamp.arquitetura.cartaoservice.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.UsuarioRepository;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CriarNovoCartaoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CriarNovoCartaoService criarNovoCartaoService;



    @Test
    void shouldSaveSuccessfullyANewCard(){
        CadastroUsuarioRequest cadastroUsuarioRequest = Mockito.mock(CadastroUsuarioRequest.class);
        Usuario usuario = Mockito.mock(Usuario.class);

        Mockito.when(cadastroUsuarioRequest.getTipoCartao()).thenReturn(TipoCartao.OURO);
        Mockito.when(cadastroUsuarioRequest.getIdentificador()).thenReturn("00000000000");
        Mockito.when(cadastroUsuarioRequest.getNome()).thenReturn("Jose Joao da Silva");
        Mockito.when(usuarioRepository.findById(Mockito.anyString())).thenReturn(Optional.of(usuario));

        criarNovoCartaoService.execute(cadastroUsuarioRequest);

        ArgumentCaptor<Cartao> cartaoArgumentCaptor = ArgumentCaptor.forClass(Cartao.class);
        Mockito.verify(cartaoRepository,Mockito.times(1))
                .save(cartaoArgumentCaptor.capture());
        Cartao cartaoSalvo = cartaoArgumentCaptor.getValue();
        Assertions.assertEquals(LocalDate.now().plusYears(5),
                cartaoSalvo.getVencimentoCartao());
        Assertions.assertEquals(3,cartaoSalvo.getCodigoSeguranca().length());
        Assertions.assertEquals(12, cartaoSalvo.getNumeroCartao().length());
    }
}
