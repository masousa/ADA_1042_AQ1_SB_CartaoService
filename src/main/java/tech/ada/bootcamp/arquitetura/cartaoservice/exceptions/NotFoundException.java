package tech.ada.bootcamp.arquitetura.cartaoservice.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String entityNotFound;

    public NotFoundException(String entityNotFound) {
        this.entityNotFound = entityNotFound;
    }

}
