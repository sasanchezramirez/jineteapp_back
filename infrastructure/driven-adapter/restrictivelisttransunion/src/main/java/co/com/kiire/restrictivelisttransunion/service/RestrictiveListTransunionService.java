package co.com.kiire.restrictivelisttransunion.service;

import co.com.kiire.gateway.contract.RestrictiveListGateway;
import co.com.kiire.gateway.contract.UserGateway;
import co.com.kiire.model.User;
import co.com.kiire.model.util.ResponseCode;
import co.com.kiire.model.error.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.util.List;

//servicios tienen log, persistencia no tiene log de inicio ni final, solo de error
@Service
@RequiredArgsConstructor
public class RestrictiveListTransunionService implements RestrictiveListGateway {

    private static final Logger log = Loggers.getLogger(RestrictiveListTransunionService.class.getName());

    private final List<String> forbidden;

    /**
     * @see RestrictiveListGateway#validateList(User)
     */
    @Override
    public Mono<User> validateList(User user) {
        if (this.forbidden.isEmpty()) {
            this.forbidden.add("1234567890");
            this.forbidden.add("0987654321");
            this.forbidden.add("1111111111");
        }
        return Mono.just(user)
                .doOnSuccess(request -> log.debug("Init validateList with request {}", request))
                .flatMap(usr -> {
                    if (this.forbidden.contains(user.getCode())) {
                        return Mono.error(new CustomException(ResponseCode.KAR003));
                    } else {
                        return Mono.just(user);
                    }
                })
                .doOnSuccess(response -> log.debug("Finish validateList with response {}", response))
                .onErrorResume(exception -> {
                    log.debug("Error in validateList with exception {}", exception.getMessage());
                    if (exception instanceof CustomException) {
                        return Mono.error(exception);
                    } else {
                        return Mono.error(new CustomException(ResponseCode.KAR000));
                    }
                });
    }
}
