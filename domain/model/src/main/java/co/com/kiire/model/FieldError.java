package co.com.kiire.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FieldError {
    private final String field;
    private final String error;
}
