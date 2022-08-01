package co.com.kiire.model;

import co.com.kiire.model.util.ResponseCode;
import co.com.kiire.model.error.CustomException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.MessageFormat;

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
        if ((null == this.getName())
                || (null == this.getCode())
                || (null == this.getPassword())) {
            var exception = new CustomException(ResponseCode.KAR002);
            if (null == this.getName()) {
                var fieldError = new FieldError("name", MessageFormat.format(ResponseCode.KAR004.getHtmlMessage(), ("Nombre")));
                exception.addFieldError(fieldError);
            }
            if (null == this.getCode()) {
                var fieldError = new FieldError("code", MessageFormat.format(ResponseCode.KAR004.getHtmlMessage(), ("Código")));
                exception.addFieldError(fieldError);
            }
            if (null == this.getPassword()) {
                var fieldError = new FieldError("password", MessageFormat.format(ResponseCode.KAR004.getHtmlMessage(), ("Contraseña")));
                exception.addFieldError(fieldError);
            }
            throw exception;
        }
    }

}
