package Elis.College.RegistroPranzi.model;

import Elis.College.RegistroPranzi.exception.model.Result;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserResponse {

    Result result;
    User user;

}
