package co.ankasec.campushub.service;

import co.ankasec.campushub.model.dto.EventRequestDTO;
import co.ankasec.campushub.model.dto.EventResponseDTO;
import co.ankasec.campushub.model.entity.Club;
import co.ankasec.campushub.model.entity.Event;
import co.ankasec.campushub.repository.ClubRepository;
import co.ankasec.campushub.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;

    public EventResponseDTO createEvent(EventRequestDTO request) {
        Club club = null;
        if (request.getClubId() != null) {
            club = clubRepository.findById(request.getClubId())
                    .orElseThrow(() -> new RuntimeException("Kulüp bulunamadı!"));
        }

        Event event = Event.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .location(request.getLocation())
                .eventDate(request.getEventDate())
                .category(request.getCategory())
                .imageUrl(request.getImageUrl())
                .capacity(request.getCapacity())
                .club(club)
                .build();

        Event savedEvent = eventRepository.save(event);
        return mapToResponseDTO(savedEvent);
    }

    public List<EventResponseDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public EventResponseDTO getEventById(UUID id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etkinlik bulunamadı!"));
        return mapToResponseDTO(event);
    }

    public EventResponseDTO updateEvent(UUID id, EventRequestDTO request) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etkinlik bulunamadı!"));

        if (request.getClubId() != null) {
            Club club = clubRepository.findById(request.getClubId())
                    .orElseThrow(() -> new RuntimeException("Kulüp bulunamadı!"));
            event.setClub(club);
        }

        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setLocation(request.getLocation());
        event.setEventDate(request.getEventDate());
        event.setCategory(request.getCategory());
        event.setImageUrl(request.getImageUrl());
        event.setCapacity(request.getCapacity());

        Event updatedEvent = eventRepository.save(event);
        return mapToResponseDTO(updatedEvent);
    }

    public void deleteEvent(UUID id) {
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Etkinlik bulunamadı!");
        }
        eventRepository.deleteById(id);
    }

    public List<EventResponseDTO> searchAndFilterEvents(String category, String title) {
        List<Event> events;

        if (category != null && title != null) {
            events = eventRepository.findByCategoryContainingIgnoreCaseAndTitleContainingIgnoreCase(category, title);
        } else if (category != null) {
            events = eventRepository.findByCategoryContainingIgnoreCase(category);
        } else if (title != null) {
            events = eventRepository.findByTitleContainingIgnoreCase(title);
        } else {
            events = eventRepository.findAll();
        }

        return events.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private EventResponseDTO mapToResponseDTO(Event event) {
        return EventResponseDTO.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .location(event.getLocation())
                .eventDate(event.getEventDate())
                .category(event.getCategory())
                .imageUrl(event.getImageUrl())
                .capacity(event.getCapacity())
                .clubId(event.getClub() != null ? event.getClub().getId() : null)
                .clubName(event.getClub() != null ? event.getClub().getName() : null)
                .createdAt(event.getCreatedAt())
                .build();
    }
}