package co.com.kiire.model.error;

import co.com.kiire.model.FieldError;
import co.com.kiire.model.util.ResponseCode;

import java.io.Serial;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3508567824775716466L;

    private final ResponseCode responseCode;

    private final List<FieldError> fieldErrors;

    public CustomException(ResponseCode responseCode, String... params) {
        super(MessageFormat.format(responseCode.getHtmlMessage(), (Object[]) params));
        this.responseCode = responseCode;
        this.fieldErrors = new ArrayList<>();
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void addFieldError(FieldError fieldError) {
        this.fieldErrors.add(fieldError);
    }
}
