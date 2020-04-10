package Elis.College.RegistroPranzi.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Presence {

    private Integer totBreakfast;
    private Integer totLunch;
    private Integer totDinner;
}
