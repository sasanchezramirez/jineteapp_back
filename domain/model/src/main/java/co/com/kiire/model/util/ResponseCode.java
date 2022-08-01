package co.com.kiire.model.util;

/**
 * Enumeracion con el mapeo de los códigos de respuesta
 * Kiire(1)-nombre del micro(2)-secuencia(3)
 */
public enum ResponseCode {
    KAR000(500, "Ocurrió un error inesperado, por favor intenta mas tarde."),
    KAR001(200, "Operación exitosa."),
    KAR002(400, "Campo {0} es obligatorio."),
    KAR003(403, "Usuario se encuentra en lista restrictiva.");

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
