package co.com.kiire.apirest.util;

import co.com.kiire.apirest.dto.GenericResponseDTO;
import co.com.kiire.model.config.ResponseCode;
import co.com.kiire.model.error.FieldException;
import co.com.kiire.usecase.error.FoundRestrictiveListException;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.util.HashMap;
import java.util.Map;

public class HandlerErrorController<T> {

    private final Map<String, ResponseCode> stringResponseCodeMap = new HashMap<>();
    public HandlerErrorController (){
        this.stringResponseCodeMap.put(FoundRestrictiveListException.class.getSimpleName(), ResponseCode.KAUS002);
        this.stringResponseCodeMap.put(FieldException.class.getSimpleName(), ResponseCode.KAUS002);
    }

    private static final Logger log = Loggers.getLogger(HandlerErrorController.class.getName());

    public Mono<GenericResponseDTO<T>> addErrors(Mono<GenericResponseDTO<T>> genericResponseDROMono, String method) {
        return genericResponseDROMono.onErrorResume(exception -> {
                    log.debug("Error in {}: {} with error: {}", method, exception.getClass().getSimpleName(), exception.getMessage());
                    String messageException;
                    ResponseCode responseCode = this.stringResponseCodeMap.get(exception.getClass().getSimpleName());
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
