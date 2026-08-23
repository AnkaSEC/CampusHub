package co.ankasec.campushub.repository;

import co.ankasec.campushub.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findByCategoryContainingIgnoreCase(String category);
    List<Event> findByTitleContainingIgnoreCase(String title);
    List<Event> findByCategoryContainingIgnoreCaseAndTitleContainingIgnoreCase(String category, String title);
}