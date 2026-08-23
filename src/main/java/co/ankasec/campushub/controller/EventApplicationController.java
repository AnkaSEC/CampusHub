package co.ankasec.campushub.controller;

import co.ankasec.campushub.model.dto.EventApplicationRequestDTO;
import co.ankasec.campushub.model.dto.EventApplicationResponseDTO;
import co.ankasec.campushub.service.EventApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/event-applications")
@RequiredArgsConstructor
public class EventApplicationController {

    private final EventApplicationService applicationService;

    @PostMapping
    public ResponseEntity<EventApplicationResponseDTO> applyToEvent(@RequestBody EventApplicationRequestDTO request) {
        return ResponseEntity.ok(applicationService.applyToEvent(request));
    }

    @DeleteMapping
    public ResponseEntity<Void> cancelApplication(@RequestParam UUID userId, @RequestParam UUID eventId) {
        applicationService.cancelApplication(userId, eventId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EventApplicationResponseDTO>> getUserApplications(@PathVariable UUID userId) {
        return ResponseEntity.ok(applicationService.getUserApplications(userId));
    }
}