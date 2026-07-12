package co.ankasec.campushub.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UniversityResponseDTO {

    private UUID id;

    private String name;

    private String logoUrl;

    private String domain;
}
