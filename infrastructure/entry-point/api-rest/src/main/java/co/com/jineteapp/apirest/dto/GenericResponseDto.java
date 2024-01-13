package co.com.jineteapp.apirest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponseDto<T> {
    private Boolean success;
    private String message;
    private T data;
    private String code;

    public GenericResponseDto(T data){
        this.success = true;
        this.message = "Success";
        this.data = data;
        this.code = "OK";
    }

    public GenericResponseDto(String message, String code){
        this.success = false;
        this.message = message;
        this.data = null;
        this.code = code;
    }
}

