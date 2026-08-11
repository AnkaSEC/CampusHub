package co.ankasec.campushub.repository;

import co.ankasec.campushub.model.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClubRepository extends JpaRepository<Club, UUID> {

    @Query("""
            SELECT c FROM Club c
            WHERE (:universityId IS NULL OR c.university.id = :universityId)
              AND (
                    :q IS NULL
                    OR LOWER(c.name) LIKE LOWER(CONCAT('%', :q, '%'))
                    OR LOWER(c.description) LIKE LOWER(CONCAT('%', :q, '%'))
                  )
            """)
    List<Club> search(@Param("q") String q, @Param("universityId") UUID universityId);
}
