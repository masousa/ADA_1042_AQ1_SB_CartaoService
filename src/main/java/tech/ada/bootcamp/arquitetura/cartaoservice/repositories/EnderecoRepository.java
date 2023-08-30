package tech.ada.bootcamp.arquitetura.cartaoservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}