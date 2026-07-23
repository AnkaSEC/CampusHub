package co.ankasec.campushub.model.dto;

import co.ankasec.campushub.model.enums.VerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationResponseDTO {
    private Long id;
    private Long userId;
    private String fileUrl;
    private VerificationStatus status;
    private LocalDateTime createdAt;
}