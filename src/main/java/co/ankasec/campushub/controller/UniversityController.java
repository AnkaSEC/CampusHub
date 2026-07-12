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
    public ResponseEntity<List<UniversityResponseDTO>>  getAllUniversities() {

        var result = ResponseEntity.ok(universityService.getAllUniversities());
        return    result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniversityResponseDTO> getUniversityById(@PathVariable UUID id) {
        var result = ResponseEntity.ok(universityService.getUniversityById(id));
        return result;
    }


    @PostMapping
    public ResponseEntity<UniversityResponseDTO> createUniversity(@RequestBody UniversityRequestDTO requestDTO) {

       var result = ResponseEntity.ok(universityService.createUniversity(requestDTO));

       return result;
    }

}




