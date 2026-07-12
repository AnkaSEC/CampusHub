package co.ankasec.campushub.model.dto;

import co.ankasec.campushub.model.enums.AccountType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    private String username;
    private String name;
    private String email;
    private String password;
    private String photoUrl;
    private String bio;
    private String university;
    private AccountType accountType;
}