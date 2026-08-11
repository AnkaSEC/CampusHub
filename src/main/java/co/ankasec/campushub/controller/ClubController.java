package co.ankasec.campushub.controller;

import co.ankasec.campushub.model.dto.ClubRequestDTO;
import co.ankasec.campushub.model.dto.ClubResponseDTO;
import co.ankasec.campushub.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @GetMapping
    public ResponseEntity<List<ClubResponseDTO>> getClubs(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) UUID universityId) {
        return ResponseEntity.ok(clubService.searchClubs(q, universityId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubResponseDTO> getClubById(@PathVariable UUID id) {
        return ResponseEntity.ok(clubService.getClubById(id));
    }

    @PostMapping
    public ResponseEntity<ClubResponseDTO> createClub(@RequestBody ClubRequestDTO requestDTO) {
        return ResponseEntity.ok(clubService.createClub(requestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClubResponseDTO> updateClub(@PathVariable UUID id,
                                                      @RequestBody ClubRequestDTO requestDTO) {
        return ResponseEntity.ok(clubService.updateClub(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable UUID id) {
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }
}
