package co.ankasec.campushub.model.dto;

import lombok.Data;

@Data
public class UserProfileUpdateRequestDTO {
    private String name;
    private String department;
    private String university;
}