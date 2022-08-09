package co.com.kiire.usecase;

import co.com.kiire.gateway.UserGateway;
import co.com.kiire.model.User;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

//casos de uso no tienen log
/**
 * Clase encargada de definir la implementacion del caso de uso para obtener usuarios por nombre
 */
@RequiredArgsConstructor
public class GetUsersUseCase {

    private final UserGateway userGateway;

    /**
     * @param name Name del usuario
     * @return Mono del usuario guardado
     */
    public Flux<User> execute(String name) {
        return this.userGateway.getUsers(name);
    }
}
