package Elis.College.RegistroPranzi.model;

import Elis.College.RegistroPranzi.exception.model.Result;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@ApiModel(description = "Users")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UsersResponse {

    Result result;
    List<User> userList;

}
