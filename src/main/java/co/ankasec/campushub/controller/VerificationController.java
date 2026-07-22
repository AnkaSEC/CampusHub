package co.ankasec.campushub.controller;

import co.ankasec.campushub.model.dto.VerificationResponseDTO;
import co.ankasec.campushub.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/verifications")
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationService verificationService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VerificationResponseDTO> uploadVerification(
            @RequestParam("userId") Long userId,
            @RequestParam("file") MultipartFile file) throws IOException {

        VerificationResponseDTO response = verificationService.createVerification(userId, file);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}