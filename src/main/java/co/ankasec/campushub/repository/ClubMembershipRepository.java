package co.ankasec.campushub.repository;

import co.ankasec.campushub.model.entity.ClubMembership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClubMembershipRepository extends JpaRepository<ClubMembership, UUID> {
    List<ClubMembership> findByClubId(UUID clubId);
    boolean existsByClubIdAndStudentId(UUID clubId, UUID studentId);
    Optional<ClubMembership> findByClubIdAndStudentId(UUID clubId, UUID studentId);
}