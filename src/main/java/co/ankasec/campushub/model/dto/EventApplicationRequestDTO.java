package co.ankasec.campushub.model.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class EventApplicationRequestDTO {
    private UUID userId;
    private UUID eventId;
}