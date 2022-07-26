package co.com.kiire.usecase;

import co.com.kiire.gateway.contract.RestrictiveListGateway;
import co.com.kiire.gateway.contract.SaveUserGateway;
import co.com.kiire.model.User;
import co.com.kiire.usecase.error.FoundRestrictiveListException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * Clase encargada de definir la implementacion del caso de uso para guardar al usuario
 */
@RequiredArgsConstructor
public class SaveUserUseCase {

    private final SaveUserGateway saveUserGateway;
    private final RestrictiveListGateway restrictiveListGateway;

    /**
     * @param user
     *              Usuario
     * @return
     *              Mono del usuario guardado
     * @throws FoundRestrictiveListException
     *              Exception lanzada si el usuario se encuentra en lista restrictiva
     */
    public Mono<User> saveUserCase(User user) throws FoundRestrictiveListException {
        return Mono.just(user)
                .flatMap(usr -> {
                    user.validateUser();
                    if (!this.restrictiveListGateway.validateList(usr.getCode())) {
                        return Mono.error(new FoundRestrictiveListException("Usuario se encuentra en lista restrictiva"));
                    } else {
                        return this.saveUserGateway.saveUser(usr);
                    }
                });
    }
}
