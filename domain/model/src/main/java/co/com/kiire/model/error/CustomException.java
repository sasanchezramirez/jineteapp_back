package co.com.kiire.model.error;

import co.com.kiire.model.config.ResponseCode;

import java.io.Serial;
import java.text.MessageFormat;

public class CustomException extends RuntimeException {

    /** Serial version */
    @Serial
    private static final long serialVersionUID = 3508567824775716466L;
    /**
     * Valor que permite definir un estado para la respuesta HTTP en caso de que deba ser propagada desde el origen de la excepcion
     */
    private final ResponseCode responseCode;

    public CustomException(ResponseCode responseCode, String... params) {
        super(MessageFormat.format(responseCode.getHtmlMessage(), (Object[]) params));
        this.responseCode = responseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }
}
