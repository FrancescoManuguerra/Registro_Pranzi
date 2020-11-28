package Elis.College.RegistroPranzi.model;

import Elis.College.RegistroPranzi.exception.model.Result;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;
@ApiModel(description = "Register")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegisterResponse {

    Result result;
    List<Register> registerList;
}
