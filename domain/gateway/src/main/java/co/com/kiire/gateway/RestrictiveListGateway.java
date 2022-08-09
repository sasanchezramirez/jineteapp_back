package co.com.kiire.gateway;

import co.com.kiire.model.User;
import reactor.core.publisher.Mono;

/**
 * Interfaz requerida para consumo de listas restrictivas
 */
public interface RestrictiveListGateway {

    /**
     * Método para validar si un usuario está reportado en listas restrictivas
     *
     * @param user usuario  validar
     * @return si el usuario no está en las listas restrictivas regresa el mismo usuario,
     * de lo contrario retorna un error
     */
    Mono<User> validateList(User user);
}
