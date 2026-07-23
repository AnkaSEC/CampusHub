package co.ankasec.campushub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationRequestDTO {
    private Long userId;
    private MultipartFile file;
}