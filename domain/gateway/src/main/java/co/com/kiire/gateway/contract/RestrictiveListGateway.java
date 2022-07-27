package co.com.kiire.gateway.contract;

import co.com.kiire.model.User;
import reactor.core.publisher.Mono;

/**
 * Interfaz requerida para consumo de listas restrictivas
 */
public interface RestrictiveListGateway {
    Mono<User> validateList(User user);
}
