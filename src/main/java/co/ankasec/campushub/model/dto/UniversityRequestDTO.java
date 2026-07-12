package co.ankasec.campushub.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UniversityRequestDTO {
    private String name;
    private String logoUrl;
    private String domain;
}
