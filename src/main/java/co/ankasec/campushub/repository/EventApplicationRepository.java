package co.ankasec.campushub.repository;

import co.ankasec.campushub.model.entity.EventApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventApplicationRepository extends JpaRepository<EventApplication, UUID> {
    boolean existsByUserIdAndEventId(UUID userId, UUID eventId);
    Optional<EventApplication> findByUserIdAndEventId(UUID userId, UUID eventId);
    List<EventApplication> findByUserId(UUID userId);
    List<EventApplication> findByEventId(UUID eventId);
}