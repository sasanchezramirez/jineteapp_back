package co.com.kiire.model.config;

public enum ResponseCode {
    KAUS000(500 , "Ocurrió un error inesperado, por favor intenta mas tarde."),
    KAUS001(200 , "Operación exitosa."),
    KAUS002(403 , "");

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
