package co.com.kiire.model;

import co.com.kiire.model.error.FieldException;
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
    private Integer id;
    private String name;
    private String code;
    private String password;

    public void validateUser() {
        if (null == this.getName()) {
            throw new FieldException("Campo Nombre es obligatorio");
        } else if (null == this.getCode()) {
            throw new FieldException("Campo Código es obligatorio");
        } else if (null == this.getPassword()) {
            throw new FieldException("Campo Contraseña es obligatorio");
        }
    }
}
