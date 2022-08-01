package co.com.kiire.apirest.util;

import co.com.kiire.apirest.dto.GenericResponseDTO;
import co.com.kiire.model.util.ResponseCode;
import co.com.kiire.model.error.CustomException;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

public class HandlerErrorController<T> {

    private static final Logger log = Loggers.getLogger(HandlerErrorController.class.getName());

    public Mono<GenericResponseDTO<T>> addErrors(Mono<GenericResponseDTO<T>> genericResponseDTOMono, String method) {
        return genericResponseDTOMono.onErrorResume(exception -> {
                    log.debug("Error in {}: {} with error: {}", method, exception.getClass().getSimpleName(), exception.getMessage());
                    ResponseCode responseCode;
                    String messageException;
                    if (exception instanceof CustomException customException) {
                        responseCode = customException.getResponseCode();
                        messageException = exception.getMessage();
                    } else {
                        responseCode = ResponseCode.KAUS000;
                        messageException = responseCode.getHtmlMessage();
                    }
                    return Mono.just(new GenericResponseDTO<>(
                            responseCode,
                            messageException,
                            null));
                }
        );
    }
}
