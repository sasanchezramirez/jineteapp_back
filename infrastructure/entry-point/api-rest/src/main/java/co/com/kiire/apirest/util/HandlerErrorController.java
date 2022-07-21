package co.com.kiire.apirest.util;

import co.com.kiire.apirest.dto.GenericResponseDTO;
import co.com.kiire.model.config.ResponseCode;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

public class HandlerErrorController<T> {

    private static final Logger log = Loggers.getLogger(HandlerErrorController.class.getName());

    public Mono<GenericResponseDTO<T>> addErrors(Mono<GenericResponseDTO<T>> genericResponseDROMono) {
        return genericResponseDROMono.onErrorResume(exception -> {
                    log.debug("Error in saveUser: {} with {}", exception.getClass().getSimpleName(), exception.getMessage());
                    String messageException;
                    ResponseCode responseCode = ResponseCodeException.getResponseCode(exception.getClass().getSimpleName());
                    if (responseCode.name().equalsIgnoreCase("KAUS000")) {
                        messageException = responseCode.getHtmlMessage();
                    } else {
                        messageException = exception.getMessage();
                    }
                    return Mono.just(new GenericResponseDTO<>(
                            responseCode,
                            messageException,
                            null));
                }
        );
    }
}
