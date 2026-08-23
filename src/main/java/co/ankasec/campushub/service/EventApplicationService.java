package co.ankasec.campushub.service;

import co.ankasec.campushub.model.dto.EventApplicationRequestDTO;
import co.ankasec.campushub.model.dto.EventApplicationResponseDTO;
import co.ankasec.campushub.model.entity.Event;
import co.ankasec.campushub.model.entity.EventApplication;
import co.ankasec.campushub.model.entity.User;
import co.ankasec.campushub.repository.EventApplicationRepository;
import co.ankasec.campushub.repository.EventRepository;
import co.ankasec.campushub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventApplicationService {

    private final EventApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public EventApplicationResponseDTO applyToEvent(EventApplicationRequestDTO request) {
        if (applicationRepository.existsByUserIdAndEventId(request.getUserId(), request.getEventId())) {
            throw new RuntimeException("Bu etkinliğe zaten başvurdunuz!");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));

        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Etkinlik bulunamadı!"));

        EventApplication application = EventApplication.builder()
                .user(user)
                .event(event)
                .build();

        EventApplication saved = applicationRepository.save(application);
        return mapToResponseDTO(saved);
    }

    public void cancelApplication(UUID userId, UUID eventId) {
        EventApplication application = applicationRepository.findByUserIdAndEventId(userId, eventId)
                .orElseThrow(() -> new RuntimeException("Başvuru bulunamadı!"));
        applicationRepository.delete(application);
    }

    public List<EventApplicationResponseDTO> getUserApplications(UUID userId) {
        return applicationRepository.findByUserId(userId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private EventApplicationResponseDTO mapToResponseDTO(EventApplication application) {
        return EventApplicationResponseDTO.builder()
                .id(application.getId())
                .userId(application.getUser().getId())
                .userName(application.getUser().getName())
                .eventId(application.getEvent().getId())
                .eventTitle(application.getEvent().getTitle())
                .appliedAt(application.getAppliedAt())
                .build();
    }
}