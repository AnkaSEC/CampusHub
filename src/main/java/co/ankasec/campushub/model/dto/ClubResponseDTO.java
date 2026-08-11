package co.ankasec.campushub.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private String image;
    private UUID universityId;
    private String universityName;
}
