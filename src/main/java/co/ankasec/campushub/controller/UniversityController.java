package co.ankasec.campushub.controller;

import co.ankasec.campushub.model.dto.UniversityRequestDTO;
import co.ankasec.campushub.model.dto.UniversityResponseDTO;
import co.ankasec.campushub.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/universities")
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityService universityService;

    @GetMapping
    public ResponseEntity<List<UniversityResponseDTO>> getAllUniversities() {
        return ResponseEntity.ok(universityService.getAllUniversities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniversityResponseDTO> getUniversityById(@PathVariable UUID id) {
        return ResponseEntity.ok(universityService.getUniversityById(id));
    }

    @PostMapping
    public ResponseEntity<UniversityResponseDTO> createUniversity(@RequestBody UniversityRequestDTO requestDTO) {
        return ResponseEntity.ok(universityService.createUniversity(requestDTO));
    }

}




