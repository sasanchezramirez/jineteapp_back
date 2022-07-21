package co.com.kiire.apirest.util;

import co.com.kiire.model.config.ResponseCode;
import co.com.kiire.usecase.error.FieldException;
import co.com.kiire.usecase.error.FoundRestrictiveListException;

import java.util.concurrent.ConcurrentHashMap;

public enum ResponseCodeException {
    C001(ResponseCode.KAUS002, FoundRestrictiveListException.class.getSimpleName()),
    C002(ResponseCode.KAUS002, FieldException.class.getSimpleName());

    private final ResponseCode responseCode;
    private final String exceptionName;

    ResponseCodeException(ResponseCode responseCode, String exceptionName) {
        this.responseCode = responseCode;
        this.exceptionName = exceptionName;
    }

    public ResponseCode getResponseCode() {
        return this.responseCode;
    }

    public String getExceptionName() {
        return this.exceptionName;
    }

    private static final ConcurrentHashMap<String, ResponseCodeException> CODE_STATUS = new ConcurrentHashMap<>();

    static {
        for (ResponseCodeException responseCodeException : values()) {
            CODE_STATUS.put(responseCodeException.getExceptionName(), responseCodeException);
        }
    }

    public static ResponseCode getResponseCode(String e) {
        ResponseCodeException responseCodeException = CODE_STATUS.get(e);
        if (null == responseCodeException) {
            return ResponseCode.KAUS000;
        }
        return responseCodeException.getResponseCode();
    }
}
