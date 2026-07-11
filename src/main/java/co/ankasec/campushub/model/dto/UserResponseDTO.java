package co.ankasec.campushub.model.dto;

import co.ankasec.campushub.model.enums.AccountType;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private UUID id;
    private String username;
    private String name;
    private String email;
    private String photoUrl;
    private String bio;
    private String university;
    private AccountType accountType;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime verifiedAt;
}