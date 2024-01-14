package co.com.jineteapp.apirest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class JineteoTypesDto {
    public List<TypeOfJineteoDto> jineteo;
    public JineteoTypesDto(List<TypeOfJineteoDto> typeOfJineteoDtoList) {
        this.jineteo = typeOfJineteoDtoList;
    }
}
