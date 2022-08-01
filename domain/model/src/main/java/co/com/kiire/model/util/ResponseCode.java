package co.com.kiire.model.util;

/**
 * Enumeracion con el mapeo de los códigos de respuesta
 */
public enum ResponseCode {
    KAUS000(500, "Ocurrió un error inesperado, por favor intenta mas tarde."),
    KAUS001(200, "Operación exitosa."),
    KAUS002(400, "Campo {0} es obligatorio."),
    KAUS003(403, "Usuario se encuentra en lista restrictiva.");

    private final int status;
    private final String htmlMessage;

    ResponseCode(int status, String htmlMessage) {
        this.status = status;
        this.htmlMessage = htmlMessage;
    }

    public int getStatus() {
        return this.status;
    }

    public String getHtmlMessage() {
        return this.htmlMessage;
    }
}
