package co.com.kiire.usecase;

import co.com.kiire.gateway.contract.RestrictiveListGateway;
import co.com.kiire.gateway.contract.SaveUserGateway;
import co.com.kiire.model.User;
import co.com.kiire.usecase.error.FieldException;
import co.com.kiire.usecase.error.FoundRestrictiveListException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

    private final SaveUserGateway saveUserGateway;
    private final RestrictiveListGateway restrictiveListGateway;

    public Mono<User> saveUserCase(User user) throws FoundRestrictiveListException {
        return Mono.just(user)
                .flatMap(usr -> {
                    if (null == usr.getName()) {
                        return Mono.error(new FieldException("Campo Nombre es obligatorio"));
                    } else if (null == usr.getCode()) {
                        return Mono.error(new FieldException("Campo Código es obligatorio"));
                    } else if (null == usr.getPassword()) {
                        return Mono.error(new FieldException("Campo Contraseña es obligatorio"));
                    } else {
                        return Mono.just(usr);
                    }
                })
                .flatMap(usr -> {
                    if (this.restrictiveListGateway.validateList(usr.getCode())) {
                        return this.saveUserGateway.saveUser(usr);
                    } else {
                        return Mono.error(new FoundRestrictiveListException("Usuario se encuentra en lista restrictiva"));
                    }
                });
    }
}
