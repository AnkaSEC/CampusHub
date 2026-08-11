package co.ankasec.campushub.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubRequestDTO {
    private String name;
    private String description;
    private String image;
    private UUID universityId;
}
