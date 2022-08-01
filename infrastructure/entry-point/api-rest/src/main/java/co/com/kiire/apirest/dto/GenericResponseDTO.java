package co.com.kiire.apirest.dto;

import co.com.kiire.model.util.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Schema(description = "Modelo de salida generica")
public class GenericResponseDTO<T> {

    @Schema(description = "Código interno de la respuesta")
    private final String responseCode;

    @Schema(description = "Código http de la respuesta")
    private final int status;

    @Schema(description = "Mensaje adicional de la respuesta")
    private final String responseMessage;

    private final T data;

    public GenericResponseDTO(ResponseCode responseCode, T data) {
        this.responseCode = responseCode.toString();
        this.status = responseCode.getStatus();
        this.responseMessage = responseCode.getHtmlMessage();
        this.data = data;
    }

    public GenericResponseDTO(ResponseCode responseCode, String responseMessage, T data) {
        this.responseCode = responseCode.toString();
        this.status = responseCode.getStatus();
        this.responseMessage = responseMessage;
        this.data = data;
    }

}
