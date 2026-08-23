package co.ankasec.campushub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private String university;
    private String department;
    private String studentNumber;
    private String accountType;
}