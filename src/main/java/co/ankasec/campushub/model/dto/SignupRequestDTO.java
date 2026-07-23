package co.ankasec.campushub.model.dto;

import lombok.Data;

@Data
public class SignupRequestDTO {
    private String fullName;
    private String email;
    private Long universityId;
    private String password;
    private String studentNumber;
    private String department;
}