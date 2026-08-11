package co.ankasec.campushub.model.dto;

import co.ankasec.campushub.model.enums.MembershipRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubMemberResponseDTO {
    private UUID membershipId;
    private UUID studentId;
    private String username;
    private String name;
    private String photoUrl;
    private String department;
    private String studentNumber;
    private MembershipRole role;
    private LocalDateTime joinedAt;
}
