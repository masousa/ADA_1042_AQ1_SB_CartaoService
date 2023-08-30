package tech.ada.bootcamp.arquitetura.cartaoservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, String> {
}