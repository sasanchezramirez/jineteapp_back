package co.com.kiire.model.error;

import co.com.kiire.model.util.ResponseCode;

import java.io.Serial;
import java.text.MessageFormat;

public class CustomException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3508567824775716466L;

    private final ResponseCode responseCode;

    public CustomException(ResponseCode responseCode, String... params) {
        super(MessageFormat.format(responseCode.getHtmlMessage(), (Object[]) params));
        this.responseCode = responseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }
}
