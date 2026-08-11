package co.ankasec.campushub.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubFollowResponseDTO {
    private UUID id;
    private UUID userId;
    private UUID clubId;
    private LocalDateTime createdAt;
}
