package co.com.kiire.restrictivelisttransunion.service;

import co.com.kiire.gateway.contract.RestrictiveListGateway;
import co.com.kiire.model.User;
import co.com.kiire.usecase.error.FoundRestrictiveListException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestrictiveListTransunionService implements RestrictiveListGateway {
    private final List<String> forbidden;

    @Override
    public Mono<User> validateList(User user) {
        if (this.forbidden.isEmpty()) {
            this.forbidden.add("1234567890");
            this.forbidden.add("0987654321");
            this.forbidden.add("1111111111");
        }
        return Mono.just(user)
                .flatMap(usr -> {
                    if (this.forbidden.contains(user.getCode())) {
                        return Mono.error(new FoundRestrictiveListException("Usuario se encuentra en lista restrictiva"));
                    } else {
                        return Mono.just(user);
                    }
                });
    }
}
