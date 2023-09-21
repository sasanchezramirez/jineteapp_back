package co.com.jineteapp.apirest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponseDto<T> {
    private Boolean success;
    private String message;
    private T data;

    public GenericResponseDto(T data){
        this.success = true;
        this.message = null;
        this.data = data;
    }

    public GenericResponseDto(String message){
        this.success = false;
        this.message = message;
        this.data = null;
    }
}

