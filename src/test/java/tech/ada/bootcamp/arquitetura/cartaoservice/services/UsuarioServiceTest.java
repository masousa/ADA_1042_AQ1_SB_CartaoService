package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Dependente;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Endereco;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Principal;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroDependenteRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroPrincipalRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.EnderecoRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.DependenteRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.EnderecoRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.PrincipalRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @InjectMocks
    private UsuarioService service;
    @Mock
    private PrincipalRepository principalRepository;
    @Mock
    private DependenteRepository dependenteRepository;
    @Mock
    private EnderecoRepository enderecoRepository;

    private CadastroPrincipalRequest principalDto;
    private CadastroDependenteRequest dependenteDto;
    private Principal principal;
    private Dependente dependente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciarTesteUsuario();
    }

    @Test
    void criarDependente() {
        when(principalRepository.findById(anyString())).thenReturn(Optional.of(principal));
        service.criarDependente(dependenteDto);

        verify(principalRepository, times(1)).findById(principal.getIdentificador());

        ArgumentCaptor<Dependente> dependenteArgumentCaptor = ArgumentCaptor.forClass(Dependente.class);
        verify(dependenteRepository, times(1))
                .save(dependenteArgumentCaptor.capture());
        Dependente dependenteSalvo = dependenteArgumentCaptor.getValue();
        assertEquals("11111111111", dependenteSalvo.getIdentificador());
        assertEquals("00000000000", dependenteSalvo.getPrincipal().getIdentificador());
        assertEquals("Testador Dependente", dependenteSalvo.getNome());
    }

    @Test
    void criarPrincipal() {
        service.criarPrincipal(principalDto);

        ArgumentCaptor<Principal> principalArgumentCaptor = ArgumentCaptor.forClass(Principal.class);
        verify(principalRepository, times(1))
                .save(principalArgumentCaptor.capture());
        Principal titularSalvo = principalArgumentCaptor.getValue();

        ArgumentCaptor<Endereco> enderecoArgumentCaptor = ArgumentCaptor.forClass(Endereco.class);
        verify(enderecoRepository, times(1))
                .save(enderecoArgumentCaptor.capture());
        Endereco enderecoSalvo = enderecoArgumentCaptor.getValue();

        assertEquals("00000000000", titularSalvo.getIdentificador());
        assertEquals("Testador", titularSalvo.getNome());
        assertEquals("00000000000", enderecoSalvo.getPrincipal().getIdentificador());
        assertEquals("00000000", enderecoSalvo.getCep());
        assertEquals("cidade1", enderecoSalvo.getCidade());
    }

    @Test
    void getPrincipal() {

    }

    @Test
    void getAllPrincipal() {

    }

    @Test
    void getAllDependente() {

    }

    private void iniciarTesteUsuario() {
        var endereco = new EnderecoRequest("00000000", "rua1", "bairro1", "cidade1", "estado1", "complemento1", "11");
        this.principalDto = new CadastroPrincipalRequest("00000000000", "Testador", endereco, TipoCartao.PLATINA);
        this.dependenteDto = new CadastroDependenteRequest("11111111111", "00000000000", "Testador Dependente", TipoCartao.OURO);
        this.principal = new Principal(principalDto);
        this.dependente = new Dependente(dependenteDto, principal);
    }
}