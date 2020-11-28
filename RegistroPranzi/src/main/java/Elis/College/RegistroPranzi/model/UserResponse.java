package Elis.College.RegistroPranzi.model;

import Elis.College.RegistroPranzi.exception.model.Result;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@ApiModel(description = "User")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserResponse {

    Result result;
    User user;

}
