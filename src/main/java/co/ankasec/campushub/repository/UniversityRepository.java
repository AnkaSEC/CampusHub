package co.ankasec.campushub.repository;

import co.ankasec.campushub.model.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UniversityRepository extends JpaRepository<University, UUID> {
}
