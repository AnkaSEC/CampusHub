package co.ankasec.campushub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventApplicationResponseDTO {
    private UUID id;
    private UUID userId;
    private String userName;
    private UUID eventId;
    private String eventTitle;
    private LocalDateTime appliedAt;
}