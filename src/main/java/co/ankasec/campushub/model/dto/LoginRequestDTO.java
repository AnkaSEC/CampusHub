package co.ankasec.campushub.model.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}