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
public class EventResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime eventDate;
    private String category;
    private String imageUrl;
    private Integer capacity;
    private UUID clubId;
    private String clubName;
    private LocalDateTime createdAt;
}