package co.com.kiire.apirest.handler;

import co.com.kiire.apirest.dto.GenericResponseDTO;
import co.com.kiire.model.FieldError;
import co.com.kiire.model.util.ResponseCode;
import co.com.kiire.model.error.CustomException;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.util.ArrayList;
import java.util.List;

public class ErrorHandler<T> {

    private static final Logger log = Loggers.getLogger(ErrorHandler.class.getName());

    public Mono<GenericResponseDTO<T>> addErrors(Mono<GenericResponseDTO<T>> genericResponseDTOMono, String method) {
        return genericResponseDTOMono.onErrorResume(exception -> {
                    log.debug("Error in {}: {} with error: {}", method, exception.getClass().getSimpleName(), exception.getMessage());
                    ResponseCode responseCode;
                    String messageException;
                    List<FieldError> fieldErrors;
                    if (exception instanceof CustomException customException) {
                        responseCode = customException.getResponseCode();
                        messageException = customException.getMessage();
                        fieldErrors = customException.getFieldErrors();
                    } else {
                        responseCode = ResponseCode.KAR000;
                        messageException = responseCode.getHtmlMessage();
                        fieldErrors = new ArrayList<>();
                    }
                    return Mono.just(new GenericResponseDTO<>(
                            responseCode,
                            messageException,
                            null,
                            fieldErrors));
                }
        );
    }
}
