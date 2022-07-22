package co.com.kiire.gateway.contract;

/**
 * Interfaz requerida para consumo de listas restrictivas
 */
public interface RestrictiveListGateway {
    boolean validateList(String numberIdentification);
}
