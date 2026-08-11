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
public class ClubMembershipResponseDTO {
    private UUID id;
    private UUID studentId;
    private UUID clubId;
    private MembershipRole role;
    private LocalDateTime joinedAt;
}
