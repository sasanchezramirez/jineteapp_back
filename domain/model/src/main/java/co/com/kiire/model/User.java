package co.com.kiire.model;

import co.com.kiire.model.util.ResponseCode;
import co.com.kiire.model.error.CustomException;
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
            throw new CustomException(ResponseCode.KAUS002, "Nombre");
        } else if (null == this.getCode()) {
            throw new CustomException(ResponseCode.KAUS002, "Código");
        } else if (null == this.getPassword()) {
            throw new CustomException(ResponseCode.KAUS002, "Contraseña");
        }
    }
}
