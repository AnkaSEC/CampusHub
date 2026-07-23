package co.ankasec.campushub.repository;

import co.ankasec.campushub.model.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long> {
    List<Verification> findByUserId(Long userId);
}