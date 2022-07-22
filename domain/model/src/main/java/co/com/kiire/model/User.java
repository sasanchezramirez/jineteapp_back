package co.com.kiire.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase que representa al usuario en el dominio
 */
@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String name;
    private String code;
    private String password;
}
