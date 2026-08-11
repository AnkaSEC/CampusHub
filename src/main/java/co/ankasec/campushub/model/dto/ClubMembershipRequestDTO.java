package co.ankasec.campushub.model.dto;

import co.ankasec.campushub.model.enums.MembershipRole;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubMembershipRequestDTO {
    private UUID studentId;
    private UUID clubId;
    private MembershipRole role;
}
