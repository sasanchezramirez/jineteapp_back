package co.com.kiire.gateway;

import co.com.kiire.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interfaz necesaria para guardar un usuario
 */
public interface UserGateway {

    /**
     * Guarda un usuario en la base de datos
     *
     * @param user usuario que va a ser guardado
     * @return usuario guardad en la base de datos
     */
    Mono<User> saveUser(User user);

    /**
     * Obtiene todos los usuarios filtrados por nombre de usuario
     *
     * @param name nombre del usuario para filtrar
     * @return lista de usuarios filtrados por nombre de usuario
     */
    Flux<User> getUsers(String name);
}
