package co.ankasec.campushub.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubFollowRequestDTO {
    private UUID userId;
    private UUID clubId;
}
