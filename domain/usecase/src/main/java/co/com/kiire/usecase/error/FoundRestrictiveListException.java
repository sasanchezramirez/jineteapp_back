package co.com.kiire.usecase.error;

import java.io.Serial;

public class FoundRestrictiveListException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3508567824775716455L;

    public FoundRestrictiveListException(String message) {
        super(message);
    }
}
