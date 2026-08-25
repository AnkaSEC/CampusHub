package co.ankasec.campushub.model.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EventRequestDTO {
    private String title;
    private String description;
    private String location;
    private LocalDateTime eventDate;
    private String category;
    private String imageUrl;
    private Integer capacity;
    private UUID clubId;
}