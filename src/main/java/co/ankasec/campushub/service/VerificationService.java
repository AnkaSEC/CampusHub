package co.ankasec.campushub.service;

import co.ankasec.campushub.model.dto.VerificationResponseDTO;
import co.ankasec.campushub.model.entity.Verification;
import co.ankasec.campushub.model.enums.VerificationStatus;
import co.ankasec.campushub.repository.VerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class VerificationService {

    private final VerificationRepository verificationRepository;
    private final FirebaseStorageService firebaseStorageService;

    public VerificationResponseDTO createVerification(Long userId, MultipartFile file) throws IOException {
        String fileUrl = firebaseStorageService.uploadPdf(file);

        Verification verification = Verification.builder()
                .userId(userId)
                .fileUrl(fileUrl)
                .status(VerificationStatus.PENDING)
                .build();

        Verification savedVerification = verificationRepository.save(verification);

        return VerificationResponseDTO.builder()
                .id(savedVerification.getId())
                .userId(savedVerification.getUserId())
                .fileUrl(savedVerification.getFileUrl())
                .status(savedVerification.getStatus())
                .createdAt(savedVerification.getCreatedAt())
                .build();
    }
}