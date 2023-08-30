package tech.ada.bootcamp.arquitetura.cartaoservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}