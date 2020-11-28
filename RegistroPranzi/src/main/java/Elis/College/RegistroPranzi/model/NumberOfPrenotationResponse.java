package Elis.College.RegistroPranzi.model;

import Elis.College.RegistroPranzi.exception.model.Result;
import io.swagger.annotations.ApiModel;
import lombok.*;

@ApiModel(description = "Prenotation's number")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class NumberOfPrenotationResponse {

    Result result;
    Integer launch_value;
    Integer dinner_value;
}
