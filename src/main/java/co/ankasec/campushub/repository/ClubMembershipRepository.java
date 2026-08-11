package co.ankasec.campushub.repository;

import co.ankasec.campushub.model.entity.ClubMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClubMembershipRepository extends JpaRepository<ClubMembership, UUID> {
    List<ClubMembership> findByClubId(UUID clubId);
}
