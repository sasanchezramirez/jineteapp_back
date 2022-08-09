package co.com.kiire.usecase;

import co.com.kiire.gateway.RestrictiveListGateway;
import co.com.kiire.gateway.UserGateway;
import co.com.kiire.model.User;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

//casos de uso no tienen log
/**
 * Clase encargada de definir la implementacion del caso de uso para guardar al usuario
 */
@RequiredArgsConstructor
public class SaveUserUseCase {

    private final UserGateway userGateway;
    private final RestrictiveListGateway restrictiveListGateway;

    /**
     * @param user Usuario
     * @return Mono del usuario guardado
     */
    public Mono<User> execute(User user) {
        return Mono.just(user)
                .flatMap(usr -> {
                    user.validateUser();
                    return this.restrictiveListGateway.validateList(usr);
                })
                .flatMap(this.userGateway::saveUser);
    }
}
