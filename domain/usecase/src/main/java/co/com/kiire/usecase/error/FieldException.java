package co.com.kiire.usecase.error;

import java.io.Serial;

public class FieldException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3508567824775716466L;

    public FieldException(String message) {
        super(message);
    }
}
