package co.com.kiire.model.error;

import java.io.Serial;

public class UnexpectedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3508567824775716477L;

    public UnexpectedException(String message) {
        super(message);
    }
}
